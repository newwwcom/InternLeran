package com.learn.zsh.internetlearn.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by zhoushaohua on 2018/4/1.
 */

public class MenuDebugAdapter extends ArrayAdapter<String> implements AdapterView.OnItemClickListener{
    private static final String TAG = NetLogs.NETLOG + MenuDebugAdapter.class.getName();
    private String[] mResource;
    private Context mContext;
    private int mResourceViewId;

    public MenuDebugAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResourceViewId = resource;
        mResource = objects;
        NetLogs.d(TAG, "MenuDebugAdapter.");
    }


    /*public MenuDebugAdapter(@NonNull Context context, int resource, String[] textViewResource) {
        super(context, resource, textViewResourceId);
        this.mContext = context;
        this.mResourceViewId = resource;
        mResource = textViewResource;
        NetLogs.d(TAG, "MenuDebugAdapter.");
    }*/



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row = LayoutInflater.from(mContext).inflate(mResourceViewId, parent, false);
        }
        TextView menu = row.findViewById(R.id.menu_item);
        String itemText = getItem(position);//mResource[position];
        NetLogs.d(TAG, "get the itemText : " + itemText);
        menu.setText(itemText);
        return row;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
