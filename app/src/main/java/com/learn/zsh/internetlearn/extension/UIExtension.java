package com.learn.zsh.internetlearn.extension;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;

import com.learn.zsh.internetlearn.utils.NetLogs;
import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.contents.ButtonType;
import com.learn.zsh.internetlearn.contents.ConnInternetType;
import com.learn.zsh.internetlearn.contents.ContentValue;
import com.learn.zsh.surfingtheinternet.ConnentNetRunnable;
import com.learn.zsh.surfingtheinternet.MediaDownTask;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class UIExtension implements View.OnClickListener {
    private static final String TAG = NetLogs.NETLOG + UIExtension.class.getName();
    private static Context mContext;
    private static View mParentView;
    //private EditText mWebInput;
    //private ImageView mImage;
    private ServiceConnection mConn;
    private Map<String, ServiceConnection> mConnMap;
    public UIExtension(Context context){
        this.mContext = context;
    }

    public UIExtension(Context context, View pv){
        this.mContext = context;
        this.mParentView = pv;
    }

    public void setViewOnClickListener(Set<Button> set){
        Iterator<Button> iterator = set.iterator();
        NetLogs.i(TAG, "get the set size : " + set.size());
        while(iterator.hasNext()){
            iterator.next().setOnClickListener(this);
        }
    }

    public void setConn(ServiceConnection conn){
        this.mConn = conn;
    }

    public void setConnMap(Map map){
        this.mConnMap = map;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.httpclient_dbg:
                NetLogs.i(TAG, "start Activity.");
                dealAction(ButtonType.OPEN_HTTPCLIENT_DBG);
                break;
            case R.id.conn_by_get:
                NetLogs.i(TAG, "Connect internet by httpClient Get.");
                dealAction(ButtonType.CONN_BY_HTPCNT_GET);
                break;
            case R.id.conn_by_post:
                NetLogs.i(TAG, "Connect internet by httpClient Post.");
                dealAction(ButtonType.CONN_BY_HTPCNT_POST);
                break;
            case R.id.down_image:
                NetLogs.i(TAG, "dbg the button.");
                dealAction(ButtonType.IMAGE_DOWN);
                break;
            case R.id.images_down:
                NetLogs.i(TAG, "dbg the button.");
                dealAction(ButtonType.IMAGES_DOWN_GALLERY);
                break;
            case R.id.user_defind:
                NetLogs.i(TAG, "dbg the user_defind button.");
                dealAction(ButtonType.USERDEFINED_VIEW_TEST);
                break;
            case R.id.servicedebug:
                NetLogs.i(TAG, "dbg the service life.");
                dealAction(ButtonType.SERVICE_DEPAGE);
                break;
            case R.id.fragme_dbg:
                NetLogs.i(TAG, "dbg the fragment.");
                dealAction(ButtonType.FRAGMENT_DEPAGE);
                break;

            case R.id.start_ser1:
                NetLogs.i(TAG, "start service1.");
                startService(ContentValue.BACKGROUND1_SERVICE);
                break;
            case R.id.stop_ser1:
                NetLogs.i(TAG, "stop service1.");
                stopService(ContentValue.BACKGROUND1_SERVICE);
                break;
            case R.id.start_ser2:
                NetLogs.i(TAG, "start service2.");
                startService(ContentValue.BACKGROUND2_SERVICE);
                break;
            case R.id.stop_ser2:
                NetLogs.i(TAG, "stop service2.");
                stopService(ContentValue.BACKGROUND2_SERVICE);
                break;

            case R.id.bindSer1:
                NetLogs.i(TAG, "bind service1.");
                bindService(ContentValue.BACKGROUND1_SERVICE);
                break;
            case R.id.unBindser1:
                NetLogs.i(TAG, "unbind service1.");
                unBindService(ContentValue.BACKGROUND1_SERVICE);
                break;
            case R.id.bindser2:
                NetLogs.i(TAG, "bind service2.");
                bindService(ContentValue.BACKGROUND2_SERVICE);
                break;
            case R.id.unbindser2:
                NetLogs.i(TAG, "unbind service2.");
                unBindService(ContentValue.BACKGROUND2_SERVICE);
                break;

            default:
                break;
        }
    }

    private void dealAction(ButtonType type){
        switch (type){
            case OPEN_HTTPCLIENT_DBG:
                startDbgPage(mContext, ContentValue.HTTPCLINETACTION);
                break;
            case IMAGES_DOWN_GALLERY:
                startDbgPage(mContext, ContentValue.IMAGEDOWNACTION);
                break;
            case SERVICE_DEPAGE:
                startDbgPage(mContext, ContentValue.SERVICEDEBUGACTION);
                break;
            case USERDEFINED_VIEW_TEST:
                startDbgPage(mContext, ContentValue.USERDEFINDACTION);
                break;
            case CONN_BY_HTPCNT_GET:
                connNetThreadRun(ConnInternetType.HTTPCLIENT_BYGET);
                break;
            case CONN_BY_HTPCNT_POST:
                connNetThreadRun(ConnInternetType.HTTPCLIENT_BYPOST);
                break;
            case IMAGE_DOWN:
                //connNetThreadRun(ConnInternetType.GET_MEDIA_FILE);
                downMediaFileTask();
                break;
            case FRAGMENT_DEPAGE:
                //connNetThreadRun(ConnInternetType.GET_MEDIA_FILE);
                startDbgPage(mContext, ContentValue.FRAGMENTDEBUGACTION);
                break;
            default:
                break;
        }
    }

    private void startDbgPage(Context context, String action){
        if(context == null){
            NetLogs.e(TAG, "context is null, please check the context.");
            return;
        }
        try{
            Intent intent = new Intent();
            intent.setAction(action/*ContentValue.HTTPCLINETACTION*/);
            context.startActivity(intent);
        }catch (ActivityNotFoundException e){
            NetLogs.e(TAG, e.toString());
        }
    }


    private void startService(Class<?> cls){
        if(mContext == null){
            NetLogs.e(TAG, "context is null, please check the context.");
            return;
        }
        Intent intent = new Intent(mContext, cls);
        mContext.startService(intent);
    }

    private void stopService(Class<?> cls){
        if(mContext == null){
            NetLogs.e(TAG, "context is null, please check the context.");
            return;
        }
        Intent intent = new Intent(mContext, cls);
        mContext.stopService(intent);
    }

    private void bindService(Class<?> cls){
        if(mContext == null || mConnMap.isEmpty()){
            NetLogs.e(TAG, "context is null, please check the context.");
            return;
        }
        Intent intent = new Intent(mContext, cls);
        ServiceConnection conn = mConnMap.get(cls.getName());
        NetLogs.i(TAG, "bindService conn : " + conn);
        mContext.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private void unBindService(Class<?> cls){
        if(mContext == null || mConnMap.isEmpty()){
            NetLogs.e(TAG, "context is null, please check the context.");
            return;
        }
        Intent intent = new Intent(mContext, cls);
        ServiceConnection conn = mConnMap.get(cls.getName());
        try {
            mContext.unbindService(conn);
        } catch (Exception e) {
            NetLogs.e(TAG, e.toString());
        }
    }

    public void setImageGalleryAdapter(){
        if(mParentView == null)
            return;
        Gallery gallery = mParentView.findViewById(R.id.gallery_for_image);
        //gallery.setAdapter(new UIAdapter(mContext));
    }


    private void connNetThreadRun(ConnInternetType connType){
        Thread connThread = new Thread(new ConnentNetRunnable(connType));
        connThread.start();
    }

    private void downMediaFileTask(){
        MediaDownTask task = new MediaDownTask(mParentView);
        task.setFileURI(ContentValue.DBG_WEB_BAUDU_IMAGE);
        task.execute();
    }
}
