package com.learn.zsh.surfingtheinternet;

import com.learn.zsh.internetlearn.utils.NetLogs;
import com.learn.zsh.internetlearn.contents.ConnInternetType;
import com.learn.zsh.internetlearn.contents.ContentValue;
import com.learn.zsh.surfingtheinternet.httpclientconn.HttpClientUtil;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class ConnentNetRunnable implements Runnable  {
    private static final String TAG = NetLogs.NETLOG + ConnentNetRunnable.class.getName();
    private static ConnInternetType mConnType = ConnInternetType.WEB_OFFLINE;
    public ConnentNetRunnable(ConnInternetType type){
        this.mConnType = type;
    }

    @Override
    public void run() {
        if(mConnType == ConnInternetType.HTTPCLIENT_BYGET){
            HttpClientUtil.surfingInternetByGet(ContentValue.DBG_WEB_BAIDU);
        }else if(mConnType == ConnInternetType.HTTPCLIENT_BYPOST){
            HttpClientUtil.surfingInternetByPost(ContentValue.DBG_WEB_TAOBAO_IP);
        }

    }
}
