package com.learn.zsh.internetlearn.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class NetLogs {
    public static final String NETLOG = "INT_";
    public static final String DEFAULT_TAG = "NetLogs";
    public static final String DEFAULT_MSG = "Check your log info is true.";

    public static void begin(String tag, String msg){
        if(logInfoInvalid(tag, msg)) {
            defaultLog();
            return;
        }
        Log.v(tag,msg + " begin.");
    }

    public static void end(String tag, String msg){
        if(logInfoInvalid(tag, msg)) {
            defaultLog();
            return;
        }
        Log.v(tag,msg + " end.");
    }

    public static void i(String tag, String msg){
        if(logInfoInvalid(tag, msg)) {
            defaultLog();
            return;
        }
        Log.i(tag, msg);
    }

    public static void d(String tag, String msg){
        if(logInfoInvalid(tag, msg)) {
            defaultLog();
            return;
        }
        Log.d(tag, msg);
    }

    public static void e(String tag, String msg){
        if(logInfoInvalid(tag, msg)) {
            defaultLog();
            return;
        }
        Log.e(tag, msg);
    }

    private static boolean logInfoInvalid(String tag, String msg){
        boolean infoIsInvalid = false;
        if(tag != null && msg != null){
            return infoIsInvalid;
        }
        return true;
    }

    private static void defaultLog(){
        Log.e(DEFAULT_TAG, DEFAULT_MSG);
    }

    public static void printfTaskId(String tag, Activity context){
        Log.d(tag, " TaskId: " + context.getTaskId() + " hasCode:" + context.hashCode());
    }

    public static final String getProcessName(String tag, Activity context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List list = am.getRunningAppProcesses();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (iterator.next());
            try {
                Log.d(tag, "GetProcessName Pid: " + info.pid);
                if(info.pid == context.getTaskId()){
                    return info.processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
