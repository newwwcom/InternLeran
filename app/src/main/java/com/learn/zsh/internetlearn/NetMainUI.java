package com.learn.zsh.internetlearn;

import android.os.Bundle;
import android.widget.Button;

import com.learn.zsh.internetlearn.pageview.BaseNetUIActivity;
import com.learn.zsh.internetlearn.extension.UIExtension;
import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.HashSet;
import java.util.Set;

public class NetMainUI extends BaseNetUIActivity {
    private static final String TAG = NetLogs.NETLOG + NetMainUI.class.getSimpleName();
    private Button mHttpClientDbg, mImageDown, mUserDefined, mServiceDebug, mFragmentDebug;
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

        mServiceDebug = findViewById(R.id.servicedebug);
        if(mServiceDebug != null) buttonSet.add(mServiceDebug);

        mFragmentDebug = findViewById(R.id.fragme_dbg);
        if(mFragmentDebug != null) buttonSet.add(mFragmentDebug);

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
