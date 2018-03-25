package com.learn.zsh.surfingtheinternet;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.learn.zsh.internetlearn.NetLogs;
import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.utils.ProcessInfoUtil;
import com.learn.zsh.surfingtheinternet.httpclientconn.HttpClientUtil;

import org.apache.http.HttpEntity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class MediaDownTask extends AsyncTask<String, Object, Bitmap> {
    //public abstract class AsyncTask<Params, Progress, Result>
    //在此例中，Params泛型是String类型，Progress泛型是Object类型，Result泛型是Bitmap类型
    private static final String TAG = NetLogs.NETLOG + MediaDownTask.class.getName();
    private static View mParentView;
    private ImageView downImage;
    private ProgressBar bar;
    private String mFileURI;
    private Long startTime = -1L;

    public MediaDownTask(View pv){
        this.mParentView = pv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(mParentView != null){
            downImage = mParentView.findViewById(R.id.imageView);
            bar = mParentView.findViewById(R.id.progressBar);
            bar.setVisibility(View.GONE);
        }
        startTime = System.currentTimeMillis();

    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        publishProgress();
        InputStream in = null;
        HttpEntity entity = HttpClientUtil.surfingInternetDown(getFileURI());
        Bitmap image = null;
        try {
            if(entity != null){
                in = entity.getContent();
            }
            image = ProcessInfoUtil.downFileStreamToBitmap(in);
        } catch (IOException e) {
            NetLogs.e(TAG, "MediaDownTask doInBackground : " + e.toString());
        }

        return image;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        bar.setVisibility(View.VISIBLE);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        bar.setVisibility(View.GONE);
        downImage.setImageBitmap(image);
        NetLogs.i(TAG, "MediaDownTask use time : " + (System.currentTimeMillis() - startTime));
        startTime = -1L;
        super.onPostExecute(image);
    }

    public void setFileURI(String uri){
        this.mFileURI = uri;
    }

    public String getFileURI(){
        return mFileURI;
    }
}
