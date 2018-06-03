package com.learn.zsh.internetlearn.debugpage;

import android.os.Bundle;
import android.widget.ListView;

import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.viewextension.MenuDebugAdapter;
import com.learn.zsh.internetlearn.utils.NetLogs;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class DefinedViewDbg extends BaseNetUIActivity {
    private static final String TAG = NetLogs.NETLOG + DefinedViewDbg.class.getName();
    //private View mParentView;
    private ListView mListView;
    private MenuDebugAdapter mDebugAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetLogs.d(TAG, "onCreate.");
        //setContentView(R.layout.userdefined_layout);
        setContentView(R.layout.list_view);
        initSource();
    }

    private void initSource() {
        NetLogs.d(TAG, "initSource.");
        //mParentView = findViewById(R.id.list_view_parent);
        mListView = findViewById(R.id.listview);
        //获取资源需要在onCreate中去获取，不能在声明字段的时候去直接操作
        mDebugAdapter = new MenuDebugAdapter(this, R.layout.list_item, getResources().getStringArray(R.array.debug_menu));
        mListView.setAdapter(mDebugAdapter);
    }
}
