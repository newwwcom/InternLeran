package com.learn.zsh.external.fuction.filecache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.learn.zsh.contents.ContentValue;
import com.learn.zsh.external.fuction.filecache.utils.FileUtil;
import com.learn.zsh.external.fuction.filecache.utils.ValuesTransformUtil;
import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.utils.NetLogs;
import com.learn.zsh.mylibrary.devkit.cache.DiskLruCache;
//import com.learn.zsh.external.fuction.filecache.devkit.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoushaohua on 2018/5/12.
 */

public class ImageLoader {
    private static final String TAG = NetLogs.NETLOG + "ImageLoader";
    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;
    private Context mContext;
    private boolean mIsDiskLruCacheCreated = false;

    private final static int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private final static int CORE_POOL_SIZE = CPU_COUNT + 1;
    private final static int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE * 2 + 1;
    private final static long KEEP_ALIVE = 10L;

    private final static int IMAGE_TAG_KEY_URI = R.id.imageloader_uri;
    private final static int MESSAGE_HANDLER_RESULT = 1;

    private final static ThreadFactory loadImageThreadFactory = new ThreadFactory() {
        //AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减;
        //使用场景: AtomicInteger提供原子操作来进行Integer的使用，因此十分适合高并发情况下的使用。
        final AtomicInteger count = new AtomicInteger(1);
        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "ImageLoader#" + count.getAndIncrement());
        }
    };

    private static final Executor IMAGE_LOADER_THREAD_POOL = new ThreadPoolExecutor(
            CORE_POOL_SIZE,  //核心线程数
            MAXIMUM_POOL_SIZE,  //最大线程数
            KEEP_ALIVE,  //空闲等待时间
            TimeUnit.SECONDS,  //等待时间单位
            new LinkedBlockingQueue<Runnable>(),  //阻塞队列
            loadImageThreadFactory);

    private Handler mImageHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            String uri = (String) imageView.getTag(IMAGE_TAG_KEY_URI);
            if(uri.equals(result.url)){
                imageView.setImageBitmap(result.bitmap);
            } else {
                NetLogs.w(TAG, "set bitmap bitmap, but url has changed,ignored!");
            }
        }
    };

    private ImageResizer mImageResizer = new ImageResizer();

    public static ImageLoader build(Context context){
        return new ImageLoader(context);
    }

    private ImageLoader(Context context){
        this.mContext = context.getApplicationContext();
        int memorySize = (int) (Runtime.getRuntime().maxMemory() / 1024);  //获取当前应用的运行内存大小
        int cacheSize = memorySize / 8;  //内存缓存为运行缓存的 1/8

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };

        File diskCacheDir = FileUtil.getDiskCacheDir(mContext, "cacheBitmap");
        if(!diskCacheDir.exists()){
            diskCacheDir.mkdir();
        }

        //当前缓存的文件夹可用空间（也就是磁盘的剩余空间）大于限制大小时，才能去创建磁盘缓存；否则失效，不创建磁盘缓存
        if(FileUtil.getUseableSpace(diskCacheDir) > ContentValue.IMAGE_DISKCACHE_SIZE){
            try {
                //第三个参数valueCount:表示单个节点所对应数据的个数
                mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, ContentValue.IMAGE_DISKCACHE_SIZE);
                mIsDiskLruCacheCreated = true;
            } catch (IOException e) {
                NetLogs.e(TAG, "Create diskLruCache error, info is : " + e.toString());
            }
        }
    }

    public void bindImageView(final String uri, final ImageView image){
        bindImageView(uri, image, 0, 0);
    }

    public void bindImageView(final String uri, final ImageView image, final int reqWidth, final int reqHeight){
        NetLogs.begin(TAG, "bindImageView");
        image.setTag(IMAGE_TAG_KEY_URI, uri);
        Bitmap bitmap = loadBitmapFromMemoryCache(ValuesTransformUtil.hashKeyFromUrl(uri));
        if(bitmap != null){
            image.setImageBitmap(bitmap);
            return;
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmap(uri, reqWidth, reqHeight);
                if (bitmap != null){
                    LoaderResult imageResult = new LoaderResult(uri, bitmap, image);
                    mImageHandler.obtainMessage(MESSAGE_HANDLER_RESULT, imageResult).sendToTarget();
                }
            }
        };

        IMAGE_LOADER_THREAD_POOL.execute(runnable);
        NetLogs.end(TAG, "bindImageView");
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap){
        if(getBitmapCacheFromCache(key) == null){
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapCacheFromCache(String key) {
        return mMemoryCache.get(key);
    }

    private Bitmap loadBitmap(String url, int reqWidth, int reqHeight) {
        NetLogs.begin(TAG, "loadBitmap");
        String hashKey = ValuesTransformUtil.hashKeyFromUrl(url);
        //可以直接调用getBitmapCacheFromCache(hashKey)；如下方法可读性好些，流程会清晰些
        Bitmap bitmap = loadBitmapFromMemoryCache(hashKey);
        if(bitmap != null){
            NetLogs.d(TAG, "load image from memorycache, url : " + url);
            return bitmap;
        }

        try {
            bitmap = loadBitmapFromDiskLruCache(hashKey, reqWidth, reqHeight);
            if(bitmap != null){
                NetLogs.d(TAG, "load image from diskLruCache, url : " + url);
                return bitmap;
            }

            bitmap = loadBitmapFromInternet(url, reqWidth, reqHeight);
            NetLogs.d(TAG, "load image from internet, url : " + url);
        } catch (IOException e) {
            NetLogs.e(TAG, "loadBitmap error, info is : " + e.toString());
        }

        if(bitmap == null && !mIsDiskLruCacheCreated){
            NetLogs.w(TAG, "encounter error, diskLruCache is not created.");
            bitmap = downLoadBitmapFromUrl(url);
        }
        NetLogs.end(TAG, "loadBitmap");
        return bitmap;
    }

    private Bitmap loadBitmapFromMemoryCache(String hashKey){
        Bitmap loadBitmap = getBitmapCacheFromCache(hashKey);
        return loadBitmap;
    }

    private Bitmap loadBitmapFromInternet(String url, int reqWidth, int reqHeight) throws IOException {
        NetLogs.begin(TAG, "loadBitmapFromInternet");
        if(Looper.myLooper() == Looper.getMainLooper()){
            NetLogs.w(TAG, "Load image in mainThread, it's not recommended!");
        }

        if(mDiskLruCache == null){
            return null;
        }
        String hashKey = ValuesTransformUtil.hashKeyFromUrl(url);
        DiskLruCache.Editor editor = mDiskLruCache.edit(hashKey);

        if(editor != null){
            OutputStream fo = editor.newOutputStream(ContentValue.DISK_CACHE_INDEX);
            if(downBitmapToStream(url, fo)){
                editor.commit();
            } else {
                editor.abort();
            }
            mDiskLruCache.flush();
        }
        NetLogs.end(TAG, "loadBitmapFromInternet");
        return loadBitmapFromDiskLruCache(hashKey, reqWidth, reqHeight);
    }

    private Bitmap loadBitmapFromDiskLruCache(String hashKey, int reqWidth, int reqHeight) throws IOException {
        NetLogs.begin(TAG, "loadBitmapFromDiskLruCache");
        if(Looper.myLooper() == Looper.getMainLooper()){
           NetLogs.e(TAG, "Load image in mainThread, it's not recommended!");
        }

        if(mDiskLruCache == null){
            return null;
        }
        Bitmap bitmap = null;
        final DiskLruCache.Snapshot snapshot = mDiskLruCache.get(hashKey);
        if(snapshot != null){
            FileInputStream fileInputStream = (FileInputStream) snapshot.getInputStream(ContentValue.DISK_CACHE_INDEX);
            FileDescriptor fd = fileInputStream.getFD();
            bitmap = mImageResizer.decodeSampleBitmapFromFileDescriptor(fd, reqWidth, reqHeight);
            if(bitmap != null){
                addBitmapToMemoryCache(hashKey, bitmap);
            }
        }
        NetLogs.end(TAG, "loadBitmapFromDiskLruCache");
        return bitmap;
    }

    private boolean downBitmapToStream(String url, OutputStream fo) throws IOException {
        NetLogs.begin(TAG, "downBitmapToStream");
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            URL urlObject = new URL(url);
            urlConnection = (HttpURLConnection) urlObject.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), ContentValue.IO_BUFFER_SIZE);
            out = new BufferedOutputStream(fo, ContentValue.IO_BUFFER_SIZE);
            int line;
            while((line = in.read()) != -1){
                out.write(line);
            }
            return true;
        } catch (MalformedURLException e) {
            NetLogs.e(TAG, "downBitmapToStream MalformedURLException error, info is : " + e.toString());
        } catch (IOException e) {
            NetLogs.e(TAG, "downBitmapToStream IOException error, info is : " + e.toString());
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            FileUtil.closeStream(in);
            FileUtil.closeStream(out);
            NetLogs.end(TAG, "downBitmapToStream");
        }
        return false;
    }

    private Bitmap downLoadBitmapFromUrl(String uri) {
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        try {
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), ContentValue.IO_BUFFER_SIZE);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            NetLogs.e(TAG, "downBitmapToStream MalformedURLException error, info is : " + e.toString());
        } catch (IOException e) {
            NetLogs.e(TAG, "downBitmapToStream IOException error, info is : " + e.toString());
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            FileUtil.closeStream(in);
        }
        return bitmap;
    }


    private static class LoaderResult{
        public String url;
        public Bitmap bitmap;
        public ImageView imageView;

        public LoaderResult(String url, Bitmap bitmap, ImageView imageView){
            this.url = url;
            this.bitmap = bitmap;
            this.imageView  = imageView;
        }
    }

}
