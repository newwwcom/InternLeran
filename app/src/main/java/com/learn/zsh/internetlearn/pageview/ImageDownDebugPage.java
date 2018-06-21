package com.learn.zsh.internetlearn.pageview;

import android.os.Bundle;
import android.widget.GridView;

import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.extension.UIExtension;
import com.learn.zsh.internetlearn.utils.NetLogs;
import com.learn.zsh.internetlearn.viewextension.PhotoWallAdapter;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class ImageDownDebugPage extends BaseNetUIActivity {
    private static final String TAG = NetLogs.NETLOG + "ImageDownDebugPage";
    private GridView gridView;
    private UIExtension mUIExtension;

    private PhotoWallAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetLogs.printfTaskId(TAG, this);
        setContentView(R.layout.gallery_photo_wall);
        initDateSource();
    }

    private void initDateSource() {
        gridView = findViewById(R.id.photo_wall);
        photoAdapter = new PhotoWallAdapter(this);
        gridView.setAdapter(photoAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mUIExtension == null){
            initDateSource();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mUIExtension != null){
            mUIExtension = null;
        }
    }
}
