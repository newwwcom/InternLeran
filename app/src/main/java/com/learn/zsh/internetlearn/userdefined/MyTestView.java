package com.learn.zsh.internetlearn.userdefined;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.learn.zsh.internetlearn.utils.NetLogs;

/**
 * Created by zhoushaohua on 2018/3/25.
 */

public class MyTestView extends View {
    private static final String TAG = NetLogs.NETLOG + MyTestView.class.getName();

    private Paint mPaint;
    private Point mCenter;
    private float mRadius;  //要画的圆的半径

    //通过JAVA代码构造视图是使用此方法
    public MyTestView(Context context) {
        this(context, null);
    }
    //从XML填充视图时使用该构造方法。AttributeSet包括附加到视图的XML元素时被调用
    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    //与上一个类似，但是将样式属性添加到XML元素时被调用
    public MyTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mCenter = new Point();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //确定内容的理想大小，无限制
        int widthContent = 100;
        int heightContent = 100;
        int measureWidth = getMeasurement(widthMeasureSpec, widthContent);
        int measureHeight = getMeasurement(heightMeasureSpec, heightContent);
        setMeasuredDimension(measureWidth, measureHeight);
    }

    private int getMeasurement(int measureSpec, int contentSize){
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (MeasureSpec.getMode(measureSpec)){
            case MeasureSpec.UNSPECIFIED:
                return contentSize;
            case MeasureSpec.AT_MOST:
                return Math.min(specSize, contentSize);
            case MeasureSpec.EXACTLY:
                return specSize;
            default:
                return 0;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w != oldw || h != oldh){
            mCenter.x = w / 2;
            mCenter.y = h / 2;
            mRadius = Math.min(mCenter.x, mCenter.y);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius, mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.8f, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.6f, mPaint);

        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.4f, mPaint);

        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(mCenter.x, mCenter.y, mRadius * 0.1f, mPaint);

    }
}
