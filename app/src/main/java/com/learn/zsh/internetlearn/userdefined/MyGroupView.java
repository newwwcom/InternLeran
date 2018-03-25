package com.learn.zsh.internetlearn.userdefined;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class MyGroupView extends ViewGroup {
    public MyGroupView(Context context) {
        this(context, null);
    }

    public MyGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

        if(getChildCount() > 0){
            if(widthMeasureMode == MeasureSpec.AT_MOST && heightMeasureMode == MeasureSpec.AT_MOST){
                setMeasuredDimension(getMaxWidthSize(), getTotalHeightSize());
            } else if(widthMeasureMode == MeasureSpec.AT_MOST){
                setMeasuredDimension(getMaxWidthSize(), heightMeasureMode);
            } else if(heightMeasureMode == MeasureSpec.AT_MOST){
                setMeasuredDimension(widthMeasureSpec, getMaxWidthSize());
            }
        }else{
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private int getTotalHeightSize(){
        int heightSiz = 0;
        for(int i = 0; i < getChildCount(); i++){
            //heightSiz += getChildAt(i).getHeight();
            //需要包含边距
            heightSiz += getChildAt(i).getMeasuredHeight();
        }
        return heightSiz;
    }

    private int getMaxWidthSize(){
        int widthSize = 0;
        for(int i = 0; i < getChildCount(); i++){
            int width = getChildAt(i).getMeasuredWidth();
            if(width > widthSize){
                widthSize = width;
            }
        }
        return widthSize;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int curHeight = top;
        for(int i = 0; i < getChildCount(); i++){
            View child = getChildAt(i);
            child.layout(left, curHeight, left + child.getMeasuredWidth(), curHeight + child.getMeasuredHeight());
            curHeight += child.getMeasuredHeight();
        }
    }
}
