package com.learn.zsh.surfingtheinternet.httpclientconn;

import com.learn.zsh.internetlearn.utils.NetLogs;
import com.learn.zsh.contents.ContentValue;
import com.learn.zsh.internetlearn.utils.ProcessInfoUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class HttpClientUtil {
    private static final String TAG = NetLogs.NETLOG + HttpClientUtil.class.getName();

    private static HttpClient createHttpClient(){
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, ContentValue.REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, ContentValue.CONN_TIMEOUT);
        HttpConnectionParams.setTcpNoDelay(params, true);

        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        HttpProtocolParams.setUseExpectContinue(params, true);

        HttpClient httpClient = new DefaultHttpClient(params);
        return httpClient;
    }

    public static void surfingInternetByGet(String webUri){
        HttpGet httpGet = new HttpGet(webUri);
        httpGet.addHeader(ContentValue.HEAD_KEY_NAME, ContentValue.HEAD_VALUE_NAME);

        HttpClient httpClient = createHttpClient();
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if(entity != null){
                //String result = ProcessInfoUtil.converStreamToString(entity.getContent());
                String result = ProcessInfoUtil.converEntityToString(entity);
                NetLogs.d(TAG, "状态码 ： " + statusCode);
                NetLogs.d(TAG, "请求结果 ： " + result);
            }
        } catch (IOException e) {
            NetLogs.e(TAG, "surfingInternetByGet : " + e.toString());
        }
    }

    public static void surfingInternetByPost(String webUri){
        HttpPost httpPost = new HttpPost(webUri);
        httpPost.addHeader(ContentValue.HEAD_KEY_NAME, ContentValue.HEAD_VALUE_NAME);
        HttpClient httpClient = createHttpClient();
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair(ContentValue.IP_FOR_PAIRNAME, ContentValue.IP_OF_PAIRVALUE));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                int statusCode = response.getStatusLine().getStatusCode();
                //String result = ProcessInfoUtil.converEntityToString(entity);
                String result = ProcessInfoUtil.converStreamToString(entity.getContent());
                NetLogs.d(TAG, "状态码 ： " + statusCode);
                NetLogs.d(TAG, "请求结果 ： " + result);
            }
        } catch (UnsupportedEncodingException e) {
            NetLogs.e(TAG, "surfingInternetByPost : " + e.toString());
        } catch (IOException e) {
            NetLogs.e(TAG, "surfingInternetByPost : " + e.toString());
        }
    }

    public static HttpEntity surfingInternetDown(String webUri){
        HttpGet get = new HttpGet(webUri);
        get.addHeader(ContentValue.HEAD_KEY_NAME, ContentValue.HEAD_VALUE_NAME);
        HttpClient client = createHttpClient();
        HttpEntity result = null;
        int status = -1;
        try {
            HttpResponse response = client.execute(get);
            status = response.getStatusLine().getStatusCode();
            NetLogs.d(TAG, "状态码 ： " + status);
            result = response.getEntity();
        } catch (IOException e) {
            NetLogs.e(TAG, "surfingInternetDown : " + e.toString());
        }
        return status == ContentValue.CONN_SUCCES ? result : null;
    }
}
