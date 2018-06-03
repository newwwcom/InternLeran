package com.learn.zsh.internetlearn.debugpage.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.utils.NetLogs;

/**
 * Created by zhoushaohua on 2018/4/10.
 */

public class FragmentLearnUI extends FragmentActivity {
    private static final String TAG = NetLogs.NETLOG + "FragmentLearnUI";
    private FragmentUIExtension mUIExtension;
    private View mContianView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_new);
        initSource();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUIExtension != null){
            mUIExtension = null;
        }
    }
}
