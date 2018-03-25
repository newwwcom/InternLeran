package com.learn.zsh.internetlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.learn.zsh.internetlearn.extension.UIExtension;
import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.HashSet;
import java.util.Set;

public class NetMainUI extends AppCompatActivity {
    private static final String TAG = NetLogs.NETLOG + NetMainUI.class.getSimpleName();
    private Button mHttpClientDbg, mImageDown, mUserDefined;
    private UIExtension uiExtension;
    private Set<Button> buttonSet = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_show_ui);
        NetLogs.printfTaskId(TAG, this);
        initSource();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(uiExtension == null){
            initSource();
        }
    }

    private void initSource(){
        uiExtension = new UIExtension(this);
        mHttpClientDbg = findViewById(R.id.httpclient_dbg);
        if(mHttpClientDbg != null) buttonSet.add(mHttpClientDbg);
        mImageDown = findViewById(R.id.images_down);
        if(mImageDown != null) buttonSet.add(mImageDown);
        mUserDefined = findViewById(R.id.user_defind);
        if(mUserDefined != null) buttonSet.add(mUserDefined);
        uiExtension.setViewOnClickListener(buttonSet);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(uiExtension != null){
            uiExtension = null;
        }
    }
}
