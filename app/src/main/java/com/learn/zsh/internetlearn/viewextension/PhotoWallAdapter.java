package com.learn.zsh.internetlearn.viewextension;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.learn.zsh.contents.ContentValue;
import com.learn.zsh.external.fuction.filecache.ImageLoader;
import com.learn.zsh.internetlearn.R;
import com.learn.zsh.internetlearn.extension.ViewHold;
import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoushaohua on 2018/5/13.
 */

public class PhotoWallAdapter extends BaseAdapter {
    private static final String TAG = NetLogs.NETLOG + "PhotoWallAdapter";
    private Context mContext;
    private List<String> mUrList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private ImageLoader mImageLoader;
    private Drawable mDefaultBitmapDrawable;
    public PhotoWallAdapter(Context cnx){
        this.mContext = cnx;
        mLayoutInflater = LayoutInflater.from(mContext);
        mImageLoader = ImageLoader.build(mContext);
        mDefaultBitmapDrawable = mContext.getResources().getDrawable(R.drawable.image_default);
        for(String uri : ContentValue.IMAGE_URL_LIST){
            mUrList.add(uri);
        }
    }


    @Override
    public int getCount() {
        NetLogs.i(TAG, "getCount mUrList.size : " + mUrList.size());
        return mUrList.size();
    }

    @Override
    public String getItem(int position) {
        return mUrList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.gallery_photo_item_view, parent, false);
            viewHold = new ViewHold();
            viewHold.imageView = convertView.findViewById(R.id.photo_item);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        ImageView imageView = viewHold.imageView;
        final String tag = (String)imageView.getTag();
        final String uri = getItem(position);
        NetLogs.i(TAG, "getView, tag : " + tag);
        NetLogs.i(TAG, "getView, uri : " + uri);
        //imageView.setImageDrawable(mDefaultBitmapDrawable);
        if(!uri.equals(tag)){
            imageView.setImageDrawable(mDefaultBitmapDrawable);
        }
        imageView.setTag(uri);
        mImageLoader.bindImageView(uri, imageView);
        return convertView;
    }
}
