package com.learn.zsh.internetlearn.debugpage;

import android.os.Bundle;
import android.view.View;

import com.learn.zsh.internetlearn.BaseNetUIActivity;
import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.extension.UIExtension;
import com.learn.zsh.internetlearn.utils.NetLogs;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class ImageDownDebugPage extends BaseNetUIActivity {
    private static final String TAG = NetLogs.NETLOG + "ImageDownDebugPage";
    //private Gallery gellery;
    private View mContentView;
    private UIExtension mUIExtension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetLogs.printfTaskId(TAG, this);
        setContentView(R.layout.gallery_image_down);
        initSource();
    }

    private void initSource() {
        mContentView = findViewById(R.id.gellery_view_parent);
        mUIExtension = new UIExtension(this, mContentView);
        mUIExtension.setImageGalleryAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mUIExtension == null){
            initSource();
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
