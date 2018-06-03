package com.learn.zsh.platformservice;


/**
 * Created by zhoushaohua on 2018/6/3.
 */

public interface IPlatformServiceManager {
    <T> void bindService(Class<T> clazz, T service);
    void unbindService(Class clazz);
    <T> T getService(Class<T> clazz);
}
