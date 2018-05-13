package com.learn.zsh.external.fuction.filecache.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import com.learn.zsh.internetlearn.utils.NetLogs;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/**
 * Created by zhoushaohua on 2018/5/12.
 */

public class FileUtil {
    private static final String TAG = NetLogs.NETLOG + "FileUtil";

    public static File getDiskCacheDir(Context context, String uniqueName) {
        boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if(externalStorageAvailable){
            cachePath = context.getExternalCacheDir().getPath();  //这个是外部SD卡缓存路径
        }else{
            cachePath = context.getCacheDir().getPath();  //这个是内部SD卡缓存路径
        }
        NetLogs.d(TAG, "show path1 = " + context.getExternalCacheDir().getPath() + " and path2 = " + context.getCacheDir().getPath());
        return new File(cachePath + File.separator + uniqueName);
    }

    public static long getUseableSpace(File filePath) {
        NetLogs.d(TAG, "build version number : " + Build.VERSION.SDK_INT);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
            return filePath.getUsableSpace();
        }
        final StatFs statFs = new StatFs(filePath.getPath());
        return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
    }

    public static void closeStream(Closeable stream) {
        if(stream != null){
            try {
                stream.close();
            } catch (IOException e) {
                NetLogs.e(TAG, "closeStream error, info is : " + e.toString());
            }
        }
    }

}
