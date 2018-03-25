package com.learn.zsh.internetlearn.debugpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.extension.UIExtension;
import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class HttpClientDebugPage extends AppCompatActivity {
    private static final String TAG = NetLogs.NETLOG + HttpClientDebugPage.class.getSimpleName();
    private View mContentView;

    private Button mConnByGet, mConnByPost, dbgButton;
    private UIExtension mUIExtension;
    private Set<Button> buttonSet = new HashSet<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.htpclient_layout);
        NetLogs.printfTaskId(TAG, this);
        initSource();
    }

    private void initSource() {
        mContentView = findViewById(R.id.httpclient_layout);
        mUIExtension = new UIExtension(this, mContentView);

        mConnByGet = findViewById(R.id.conn_by_get);
        if(mContentView != null) buttonSet.add(mConnByGet);
        mConnByPost = findViewById(R.id.conn_by_post);
        if(mConnByPost != null) buttonSet.add(mConnByPost);
        dbgButton = findViewById(R.id.down_image);
        if(dbgButton != null) buttonSet.add(dbgButton);

        mUIExtension.setViewOnClickListener(buttonSet);
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
