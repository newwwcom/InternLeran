package com.learn.zsh.internetlearn.debugpage.fragment;

import android.content.Context;
import android.view.View;

import com.learn.zsh.internetlearn.utils.NetLogs;

/**
 * Created by zhoushaohua on 2018/4/11.
 */

public class FragmentUIExtension implements View.OnClickListener {
    private static final String TAG = NetLogs.NETLOG + "FragmentUIExtension";
    private Context mContext;

    public FragmentUIExtension(Context context){
        this.mContext = context;
    }



    @Override
    public void onClick(View v) {

    }

}
