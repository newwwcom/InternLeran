package com.learn.zsh.internetlearn.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by zhoushaohua on 2018/5/13.
 */

public class PhoneStateInfoUtil {
    private static final String TAG = NetLogs.NETLOG + "PhoneStateInfoUtil";
    public static boolean isInWifiEnvironment(Context context){
        NetLogs.i(TAG, " isInWifiEnvironment.");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info != null && info.getType() == connectivityManager.TYPE_WIFI){
            return true;
        }
        return false;
    }
}
