package com.learn.zsh.internetlearn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.zsh.internetlearn.R;

/**
 * Created by zhoushaohua on 2018/6/3.
 */

public class ThreadFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thread_fragment_ui, container, false);
    }
}
