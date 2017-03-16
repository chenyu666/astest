package com.example.slidingmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Created by HSAEE on 2017/3/15.
 */

public class SlidingMenu extends ViewGroup {

    private float mDownX;
    private View mLeftChild;
    private View mMenuChild;
    private Scroller mScroller;
    private boolean isClosed = true;

    private float mDowny;

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //布局之后才能显示  以控件的左上为原点确定要布局的子控件的左上坐标和右下坐标
        mLeftChild = getChildAt(0);
        mLeftChild.layout(-mLeftChild.getMeasuredWidth(), 0, 0, mLeftChild.getMeasuredHeight());
        View rightChild = getChildAt(1);
        rightChild.layout(0, 0, rightChild.getMeasuredWidth(), rightChild.getMeasuredHeight());


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //viewgroup要 测量孩子

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //把左边不可见的控件和可见的控件放在一个容器控件监听滑动实现滑动拉出效果
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                //限制滑动偏移量全部往右,全部为负数;
                float diffX = mDownX - moveX;  //如果从左往右边滑，结果是负数
                float finalx = getScrollX() + diffX;

                if (finalx > 0) {
                    scrollTo(0, 0);
                    return true;
                } else if (finalx < -mLeftChild.getMeasuredWidth()) {
                    scrollTo(-mLeftChild.getMeasuredWidth(), 0);
                    return true;
                }
                scrollBy((int) diffX, 0);
                mDownX = moveX;
                break;


            case MotionEvent.ACTION_UP:
                //往右滑动的越多 越小
                float middle = -mLeftChild.getMeasuredWidth() / 2;
                if (getScrollX() < middle) {
                    //scrollTo(-mLeftChild.getMeasuredWidth(),0);
                    mScroller.startScroll(getScrollX(), 0, -mLeftChild.getMeasuredWidth() - getScrollX(), 0, 500);
                    invalidate();
                    isClosed = false;
                } else {

                    close();
                    /// scrollTo(0,0);
                }

                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView ivBack = (ImageView) findViewById(R.id.back);
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClosed) {
                    mScroller.startScroll(getScrollX(), 0, -mLeftChild.getMeasuredWidth() - getScrollX(), 0, 500);
                    invalidate();
                    isClosed = false;

                } else {

                    close();
                }
            }
        });
    }

    public void close() {
        mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0, 500);
        invalidate();
        isClosed = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDowny = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();
                if (Math.abs(moveX - mDownX) > Math.abs(moveY - mDowny)) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        //默认就是不拦截
        return super.onInterceptTouchEvent(ev);
    }
}
