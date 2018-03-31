package com.learn.zsh.internetlearn.service;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.learn.zsh.internetlearn.utils.NetLogs;


/**
 * Created by zhoushaohua on 2018/3/31.
 */

public class BackgroundService extends Service {
    private static final String TAG = NetLogs.NETLOG + BackgroundService.class.getName();
    private ServiceBinder mBinder;
    @Override
    public void onCreate() {
        super.onCreate();
        NetLogs.i(TAG, "onCreate.");
        mBinder = new ServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NetLogs.i(TAG, "onStartCommand.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        NetLogs.i(TAG, "onDestroy.");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        NetLogs.i(TAG, "onBind.");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        NetLogs.i(TAG, "onUnbind.");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        NetLogs.i(TAG, "onRebind.");
        super.onRebind(intent);
    }
}
