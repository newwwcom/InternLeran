package com.learn.zsh.external.fuction.filecache.devkit.planforinterface;

import android.graphics.Bitmap;

/**
 * Created by ZhouShaohua on 2018/6/17.
 */
public interface IImageCache {
    Bitmap loadBitmap(String url, int reqWidth, int reqHeight);
}
