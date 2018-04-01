package com.learn.zsh.internetlearn.userdefined;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.learn.zsh.internetlearn.utils.NetLogs;

/**
 * Created by zhoushaohua on 2018/3/26.
 */

/*在View类中有四个构造函数，涉及到多个参数，
 * Context：上下文，这个不用多说
 * AttributeSet attrs ： 从xml中定义的参数
 * int defStyleAttr ：主题中优先级最高的属性
 * int defStyleRes  ： 优先级次之的内置于View的style
 * 在android中的属性可以在多个地方进行赋值，涉及到的优先级排序为：
 * Xml直接定义 > xml中style引用 > defStyleAttr > defStyleRes > theme直接定义
 */
public class MoveView extends View {
    private static final String TAG = NetLogs.NETLOG + MoveView.class.getName();

    private Scroller mScroller;
    //通过JAVA代码直接实例化的，使用该构造方法（View v = new View(this);）
    public MoveView(Context context) {
        this(context, null);
    }
    //在XML文件被引用时使用该构造方法
    public MoveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    //如果在XML文件中引用，又使用了样式属性（其中的优先级：xml定义>style>theme）时，使用该构造方法
    public MoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    private int lastX, lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int curX = (int)event.getX();
        int curY = (int)event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = curX;
                lastY = curY;
                NetLogs.d(TAG, "Get the touchDown coordinate : (" + lastX + ", " + lastY + ").");
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = curX - lastX;
                int offsetY = curY - lastY;
                //layout(getLeft() + offsetX, getTop() + offsetY,getRight() + offsetX, getBottom() + offsetY);

                //offsetLeftAndRight(offsetX);
                //offsetTopAndBottom(offsetY);

                /*ConstraintLayout比较特殊，需要使用限制,待后续分析下*/
                //ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)getLayoutParams();
                //layoutParams.horizontalBias = offsetX;
                //layoutParams.verticalBias = offsetY;
                //setLayoutParams(layoutParams);

                //((View)getParent()).scrollBy(-offsetX, -offsetY);
                smoothScrollerTo(-offsetX, 0);

                break;
            case MotionEvent.ACTION_UP:
                NetLogs.d(TAG, "Get the touchUp coordinate : (" + event.getX() + ", " + event.getY() + ").");
                //layout();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((ViewGroup) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();  //调用该方法不断的重绘
        }
    }

    public void smoothScrollerTo(int destX, int destY){
        int scrollX = getScrollX();
        int lastX = destX - scrollX;
        mScroller.startScroll(scrollX, 0, lastX, 0, 2000);
        invalidate();
    }
}















