package com.learn.zsh.internetlearn.debugpage.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.fragment.Fragement2Page;
import com.learn.zsh.internetlearn.fragment.Fragment3Page;
import com.learn.zsh.internetlearn.fragment.FragmentPage;
import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhoushaohua on 2018/4/11.
 */

public class FragmentUIExtension implements View.OnClickListener {
    private static final String TAG = NetLogs.NETLOG + "FragmentUIExtension";
    private FragmentActivity mActivity;
    private View mView;
    private RadioButton mTalkButton, mContactsButton, mMoreButton;
    //private List<RadioButton> mListRadioButton = new ArrayList<>();
    private Set<RadioButton> mButtonSet = new HashSet<>();
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    public FragmentUIExtension(FragmentActivity activity){
        this.mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
    }

    public void initAndSetButtonClickListener(View view){
        this.mView = view;
        mTalkButton = mView.findViewById(R.id.radio_talkview);
        if(mTalkButton != null) mButtonSet.add(mTalkButton);

        mContactsButton = mView.findViewById(R.id.radio_contacts);
        if(mContactsButton != null) mButtonSet.add(mContactsButton);

        mMoreButton = mView.findViewById(R.id.radio_more);
        if(mMoreButton != null) mButtonSet.add(mMoreButton);

        setButtonClickListener();
    }

    private void setButtonClickListener(){
        Iterator<RadioButton> iterator = mButtonSet.iterator();
        NetLogs.i(TAG, "get the set size : " + mButtonSet.size());
        while (iterator.hasNext()){
            iterator.next().setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_talkview:
                selectFragment(new FragmentPage());
                //Toast.makeText(mActivity, "选择聊天界面", ContentValue.TOAST_SHOW_TIME).show();
                break;
            case R.id.radio_contacts:
                selectFragment(new Fragement2Page());
                break;
            case R.id.radio_more:
                selectFragment(new Fragment3Page());
                break;

            default:
                break;
        }
    }

    public void addFragment(){
        Fragment fragment = new FragmentPage();
        addFragment(fragment, "talkView");

        Fragment fragment2 = new Fragement2Page();
        addFragment(fragment, "contactsView");

        Fragment fragment3 = new Fragment3Page();
        addFragment(fragment, "moreView");
    }

    private void addFragment(Fragment fragment, String tag){
        Fragment temp = mFragmentManager.findFragmentByTag(tag);

    }

    private void selectFragment(Fragment fragment){
        mTransaction.replace(R.id.fragment_container, fragment).commit();
    }

}
