package com.learn.zsh.internetlearn.debugpage.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhoushaohua on 2018/6/3.
 */

public class FPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = NetLogs.NETLOG + "FPagerAdapter";
    private Map<Integer, Fragment> fragments = new HashMap<>();

    public FPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragmensMap(Map<Integer, Fragment> maps){
        this.fragments = maps;
    }

    @Override
    public Fragment getItem(int position) {
        NetLogs.i(TAG, "get the fragment : " + fragments.get(position));
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        NetLogs.i(TAG, "the fragment[" + position + "] has been destroyed.");
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
