package com.learn.zsh.internetlearn.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.learn.zsh.internetlearn.NetLogs;
import com.learn.zsh.internetlearn.contents.ContentValue;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class ProcessInfoUtil {
    private static final String TAG = NetLogs.NETLOG + ProcessInfoUtil.class.getName();

    public static String converStreamToString(InputStream in) throws IOException {
        NetLogs.begin(TAG, "converStreamToString");
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = bf.readLine()) != null){
            sb.append(line).append("\n");
        }
        NetLogs.end(TAG, "converStreamToString");
        return sb.toString();
    }

    public static String converEntityToString(HttpEntity entity) throws IOException {
        NetLogs.begin(TAG, "converEntityToString");
        byte[] bResultXML = EntityUtils.toByteArray(entity);
        String result = null;
        if(bResultXML != null){
            result = new String(bResultXML, ContentValue.CONTENTCHARSET);
        }
        NetLogs.end(TAG, "converEntityToString");
        return result != null ? result : ContentValue.DEFAULT_TIPS;
    }

    public static Bitmap downFileStreamToBitmap(InputStream in) throws IOException {
        NetLogs.begin(TAG, "converEntityToString");
        NetLogs.i(TAG, "InputStream is available : " +in.available());
        Bitmap image = BitmapFactory.decodeStream(in);
        NetLogs.end(TAG, "converEntityToString");
        return image;
    }
}
