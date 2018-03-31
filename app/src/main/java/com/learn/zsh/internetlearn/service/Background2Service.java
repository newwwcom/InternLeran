package com.learn.zsh.internetlearn.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.learn.zsh.internetlearn.utils.NetLogs;

/**
 * Created by zhoushaohua on 2018/3/31.
 */

public class Background2Service extends Service {
    private static final String TAG = NetLogs.NETLOG + Background2Service.class.getName();
    private ServiceBinder mBinder;
    @Override
    public void onCreate() {
        super.onCreate();
        NetLogs.i(TAG, "Service2onCreate.");
        mBinder = new ServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NetLogs.i(TAG, "Service2onStartCommand.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        NetLogs.i(TAG, "Service2onDestroy.");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        NetLogs.i(TAG, "Service2onBind.");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        NetLogs.i(TAG, "Service2onUnbind.");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        NetLogs.i(TAG, "Service2onRebind.");
        super.onRebind(intent);
    }
}
