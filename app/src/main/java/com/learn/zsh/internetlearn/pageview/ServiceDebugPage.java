package com.learn.zsh.internetlearn.pageview;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

import com.learn.zsh.internetlearn.R;
import com.learn.zsh.contents.ContentValue;
import com.learn.zsh.internetlearn.extension.UIExtension;
import com.learn.zsh.internetlearn.service.ServiceBinder;
import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhoushaohua on 2018/3/31.
 */

public class ServiceDebugPage extends BaseNetUIActivity {
    private static final String TAG = NetLogs.NETLOG + ServiceDebugPage.class.getSimpleName();
    private UIExtension mUIExtension;
    private Button mStartService1, mStopService1, mStartService2, mStopService2;
    private Button mBindService1, mUnbindService1, mBindService2, mUnbindService2;

    private Map<String, ServiceConnection> mConnMap = new HashMap<>();
    private Set<Button> mButtonSet = new HashSet<>();
    private ServiceBinder mBinder;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (ServiceBinder) service;
            NetLogs.d(TAG, "ServiceDebugPage onServiceConnected and mBinder: " + mBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection conn2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (ServiceBinder) service;
            NetLogs.d(TAG, "ServiceDebugPage onServiceConnected and mBinder: " + mBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetLogs.i(TAG, "ServiceDebugPage onCreate.");
        setContentView(R.layout.service_dbg_layout);
        initSource();
    }

    private void initSource() {
        mUIExtension = new UIExtension(this);

        mStartService1 = findViewById(R.id.start_ser1);
        mStopService1 = findViewById(R.id.stop_ser1);
        if(mStartService1 != null) mButtonSet.add(mStartService1);
        if(mStopService1 != null) mButtonSet.add(mStopService1);

        mStartService2 = findViewById(R.id.start_ser2);
        mStopService2 = findViewById(R.id.stop_ser2);
        if(mStartService2 != null) mButtonSet.add(mStartService2);
        if(mStopService2 != null) mButtonSet.add(mStopService2);

        mBindService1 = findViewById(R.id.bindSer1);
        mUnbindService1 = findViewById(R.id.unBindser1);
        if(mBindService1 != null) mButtonSet.add(mBindService1);
        if(mUnbindService1 != null) mButtonSet.add(mUnbindService1);

        mBindService2 = findViewById(R.id.bindser2);
        mUnbindService2 = findViewById(R.id.unbindser2);
        if(mBindService2 != null) mButtonSet.add(mBindService2);
        if(mUnbindService2 != null) mButtonSet.add(mUnbindService2);

        mConnMap.put(ContentValue.BACKGROUND1_SERVICE.getName(), conn);
        mConnMap.put(ContentValue.BACKGROUND2_SERVICE.getName(), conn2);
        //mUIExtension.setConn(conn);
        mUIExtension.setConnMap(mConnMap);
        mUIExtension.setViewOnClickListener(mButtonSet);

    }

    @Override
    protected void onPause() {
        super.onPause();
        NetLogs.i(TAG, "ServiceDebugPage onPause.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        NetLogs.i(TAG, "ServiceDebugPage onStop.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetLogs.i(TAG, "ServiceDebugPage onDestroy.");
    }
}
