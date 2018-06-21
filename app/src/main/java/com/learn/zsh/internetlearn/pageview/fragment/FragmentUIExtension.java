package com.learn.zsh.internetlearn.pageview.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.learn.zsh.contents.ContentValue;
import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.fragment.Fragement2Page;
import com.learn.zsh.internetlearn.fragment.Fragment3Page;
import com.learn.zsh.internetlearn.fragment.FragmentPage;
import com.learn.zsh.internetlearn.fragment.ThreadFragment;
import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhoushaohua on 2018/4/11.
 */

public class FragmentUIExtension implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private static final String TAG = NetLogs.NETLOG + "FragmentUIExtension";
    private RadioGroup radiocontainer;
    private RadioButton mNetButton, muserViewButton, mPhotoViewButton, mServiceButton;
    private Map<Integer, Fragment> fragments = new HashMap<>();
    private FragmentManager mFragmentManager;
    private ViewPager viewPager;
    private FPagerAdapter fPagerAdapter;

    public FragmentUIExtension(FragmentActivity activity){
        mFragmentManager = activity.getSupportFragmentManager();
    }

    public void initView(View view){
        radiocontainer = view.findViewById(R.id.include_parent_container_view);
        radiocontainer.setOnCheckedChangeListener(this);
        mNetButton = view.findViewById(R.id.rb_http_dbg);
        muserViewButton = view.findViewById(R.id.rb_definedview_dbg);
        mPhotoViewButton = view.findViewById(R.id.rb_photoview_dbg);
        mServiceButton = view.findViewById(R.id.rb_service_dbg);

        viewPager = view.findViewById(R.id.vpager);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(fPagerAdapter);
        viewPager.setCurrentItem(ContentValue.FRAGMENT_NET);
        setCurrentRadioButtonChecked();
    }


    public void bindFragment(){
        Fragment fragment = new FragmentPage();
        fragments.put(ContentValue.FRAGMENT_NET, fragment);

        Fragment fragment2 = new Fragement2Page();
        fragments.put(ContentValue.FRAGMENT_DEFINED, fragment2);

        Fragment fragment3 = new Fragment3Page();
        fragments.put(ContentValue.FRAGMENT_PHOTO, fragment3);

        Fragment threadFragment = new ThreadFragment();
        fragments.put(ContentValue.FRAGMENT_THREAD, threadFragment);

        fPagerAdapter = new FPagerAdapter(mFragmentManager);
        fPagerAdapter.setFragmensMap(fragments);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        NetLogs.i(TAG, "get the checkedId : " + checkedId);
        switch (checkedId) {
            case R.id.rb_http_dbg:
                viewPager.setCurrentItem(ContentValue.FRAGMENT_NET);
                break;
            case R.id.rb_definedview_dbg:
                viewPager.setCurrentItem(ContentValue.FRAGMENT_DEFINED);
                break;
            case R.id.rb_photoview_dbg:
                viewPager.setCurrentItem(ContentValue.FRAGMENT_PHOTO);
                break;
            case R.id.rb_service_dbg:
                viewPager.setCurrentItem(ContentValue.FRAGMENT_THREAD);
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        NetLogs.d(TAG, "onPageSelected, the position is : " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state == 2){
            setCurrentRadioButtonChecked();
        }
    }

    private void setCurrentRadioButtonChecked(){
        switch (viewPager.getCurrentItem()){
            case ContentValue.FRAGMENT_NET:
                mNetButton.setChecked(true);
                break;
            case ContentValue.FRAGMENT_DEFINED:
                muserViewButton.setChecked(true);
                break;
            case ContentValue.FRAGMENT_PHOTO:
                mPhotoViewButton.setChecked(true);
                break;
            case ContentValue.FRAGMENT_THREAD:
                mServiceButton.setChecked(true);
                break;
            default:
                break;
        }
    }

}
