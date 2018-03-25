package com.learn.zsh.internetlearn.extension;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;

import com.learn.zsh.internetlearn.NetLogs;
import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.contents.ButtonType;
import com.learn.zsh.internetlearn.contents.ConnInternetType;
import com.learn.zsh.internetlearn.contents.ContentValue;
import com.learn.zsh.surfingtheinternet.ConnentNetRunnable;
import com.learn.zsh.surfingtheinternet.MediaDownTask;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class UIExtension implements View.OnClickListener {
    private static final String TAG = NetLogs.NETLOG + UIExtension.class.getName();
    private static Context mContext;
    private static View mParentView;
    private EditText mWebInput;
    private ImageView mImage;
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
                NetLogs.i(TAG, "dbg the button");
                dealAction(ButtonType.IMAGE_DOWN);
                break;
            case R.id.images_down:
                NetLogs.i(TAG, "dbg the button");
                dealAction(ButtonType.IMAGES_DOWN_GALLERY);
                break;
            case R.id.user_defind:
                NetLogs.i(TAG, "dbg the user_defind button");
                dealAction(ButtonType.USERDEFINED_VIEW_TEST);
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
