package com.learn.zsh.external.fuction.filecache.devkit.plan2forclass;

import android.graphics.Bitmap;

/**
 * Created by ZhouShaohua on 2018/6/17.
 */
public abstract class AbstractImageCacheController {
    private AbstractImageCacheController mImageCacheController;
    abstract Bitmap loadBitmap(String url, int reqWidth, int reqHeight);
    //不适合使用抽象类来通过策略来实例化具体的子类的话；如果一定要这样做，这三个加载方式子类有必要做成单例的
    public AbstractImageCacheController getImageCacheController(){
        return new DownImageController();
    }
}
