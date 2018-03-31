package com.learn.zsh.internetlearn.service;

import android.os.Binder;

import com.learn.zsh.internetlearn.utils.NetLogs;

/**
 * Created by zhoushaohua on 2018/3/31.
 */

public class ServiceBinder extends Binder {

    private static final String TAG = NetLogs.NETLOG + ServiceBinder.class.getSimpleName();

    public int doSomething(){
        return 0;
    }

    public boolean getResult(){
        return true;
    }

}
