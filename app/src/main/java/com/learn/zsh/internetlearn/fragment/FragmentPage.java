package com.learn.zsh.internetlearn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.zsh.internetlearn.R;

/**
 * Created by zhoushaohua on 2018/4/10.
 */

public class FragmentPage extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_layout, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
