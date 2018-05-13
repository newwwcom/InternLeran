package com.learn.zsh.internetlearn.userdefined;


import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

/**
 * Created by zhoushaohua on 2018/5/13.
 */

public class SquareImageViewItem extends AppCompatImageView {


    public SquareImageViewItem(Context context) {
        this(context, null);
    }

    public SquareImageViewItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareImageViewItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
