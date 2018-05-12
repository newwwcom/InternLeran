package com.learn.zsh.external.fuction.filecache;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.learn.zsh.internetlearn.utils.NetLogs;

import java.io.FileDescriptor;

/**
 * Created by zhoushaohua on 2018/5/12.
 */

public class ImageResizer {
    private static final String TAG = NetLogs.NETLOG + "ImageResizer";

    public Bitmap decodeSampleBitmapFromRes(Resources res, int viewId, int reqWidth, int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, viewId, options);  //取出图片的原始宽高信息，对应的是outWidth、outHeight
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, viewId, options);
    }

    public Bitmap decodeSampleBitmapFromFileDescriptor(FileDescriptor fileDes, int reqWidth, int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDes, null, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDes, null, options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int calculateSize = 1;
        final int viewOriginWidth  = options.outWidth;
        final int viewOriginHeight = options.outHeight;
        if(viewOriginWidth > reqWidth || viewOriginHeight > reqHeight){
            final int halfWidth  = viewOriginWidth / 2;
            final int halfHeight = viewOriginHeight / 2;
            while ((halfWidth / calculateSize) > reqWidth && (halfHeight / calculateSize) > reqHeight){
                //calculateSize =  calculateSize << 1;
                calculateSize <<= 1;
            }
        }
        NetLogs.i(TAG, "The calculateSize = " + calculateSize);
        return calculateSize;
    }
}
