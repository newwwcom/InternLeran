package com.learn.zsh.internetlearn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.zsh.internetlearn.utils.NetLogs;

/**
 * Created by zhoushaohua on 2018/4/12.
 */

public class BaseFragment extends Fragment {
    private static final String TAG = NetLogs.NETLOG + "BaseFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NetLogs.d(TAG, "Fragment is : " + getClass().getSimpleName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
