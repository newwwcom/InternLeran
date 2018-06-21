package com.learn.zsh.internetlearn.pageview.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.pageview.InternEnvironment;
import com.learn.zsh.internetlearn.pageview.InternEnvironmentImpl;
import com.learn.zsh.internetlearn.utils.NetLogs;
import com.learn.zsh.platformservice.IPlatformServiceManager;
import com.learn.zsh.platformservice.ServerFactory;

/**
 * Created by zhoushaohua on 2018/4/10.
 */

public class FragmentLearnUI extends FragmentActivity {
    private static final String TAG = NetLogs.NETLOG + "FragmentLearnUI";
    private FragmentUIExtension mUIExtension;
    private View mContianView;
    private InternEnvironment environment;
    private ServerFactory serverFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_new);
        initSource();
        serverFactory = new ServerFactory();
    }

    private void initSource(){
        mUIExtension = new FragmentUIExtension(this);
        mContianView = findViewById(R.id.parent_container_view);
        mUIExtension.bindFragment();
        mUIExtension.initView(mContianView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mUIExtension == null){
            initSource();
        }
        //serverFactory.get(IPlatformServiceManager.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUIExtension != null){
            mUIExtension = null;
        }
    }
}
