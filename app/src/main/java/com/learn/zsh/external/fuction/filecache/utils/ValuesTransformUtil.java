package com.learn.zsh.external.fuction.filecache.utils;

import com.learn.zsh.internetlearn.utils.NetLogs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhoushaohua on 2018/5/12.
 */

public class ValuesTransformUtil {
    private static final String TAG = NetLogs.NETLOG + "ValuesTransformUtil";

    public static String hashKeyFromUrl(String url){
        String cacheKey;
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            cacheKey = bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            NetLogs.e(TAG, "Transform hashKey From Url error info : " + e.toString());
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    //byte 数组转换成十六进制数组
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(int index = 0;index < bytes.length; index++){
            String hex = Integer.toHexString(0xFF & bytes[index]);
            if(hex.length() == 1){
                sb.append('0');
            }
            sb.append(hex);
        }
        NetLogs.i(TAG, "bytesToHexString : " + sb.toString());
        return sb.toString();
    }
}
