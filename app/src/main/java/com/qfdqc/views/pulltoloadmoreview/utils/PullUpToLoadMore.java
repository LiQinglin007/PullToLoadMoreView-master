package com.qfdqc.views.pulltoloadmoreview.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by baoyunlong on 16/6/8.
 */
public class PullUpToLoadMore extends ViewGroup {
    public static String TAG = PullUpToLoadMore.class.getName();

    MyScrollView topScrollView, bottomScrollView;
    VelocityTracker velocityTracker = VelocityTracker.obtain();
    Scroller scroller = new Scroller(getContext());

    int currPosition = 0;
    int position1Y;
    int lastY;
    int lastX;
    public int scaledTouchSlop;//最小滑动距离
    int speed = 200;
    boolean isIntercept;

    public boolean bottomScrollVIewIsInTop = false;
    public boolean topScrollViewIsBottom = true;

    /**
     * 底部控件是否滑动到了最顶部
     */
    public static boolean isTop=false;

    public PullUpToLoadMore(Context context) {
        super(context);
        init();
    }

    public PullUpToLoadMore(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullUpToLoadMore(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        post(new Runnable() {
            @Override
            public void run() {
                topScrollView = (MyScrollView) getChildAt(0);
                bottomScrollView = (MyScrollView) getChildAt(1);
                topScrollView.setScrollListener(new MyScrollView.ScrollListener() {
                    @Override
                    public void onScrollToBottom() {
                        topScrollViewIsBottom = true;
                    }

                    @Override
                    public void onScrollToTop() {

                    }

                    @Override
                    public void onScroll(int scrollY) {

                    }

                    @Override
                    public void notBottom() {
                        topScrollViewIsBottom = false;
                    }

                });

                bottomScrollView.setScrollListener(new MyScrollView.ScrollListener() {
                    @Override
                    public void onScrollToBottom() {
                    }

                    @Override
                    public void onScrollToTop() {
                    }

                    @Override
                    public void onScroll(int scrollY) {
                        if (scrollY == 0) {
                            bottomScrollVIewIsInTop = true;
                        } else {
                            bottomScrollVIewIsInTop = false;
                        }
                    }

                    @Override
                    public void notBottom() {
                    }
                });

                position1Y = topScrollView.getBottom();
                scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //防止子View禁止父view拦截事件
        this.requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        int x = (int) ev.getX();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                lastX = x;
                break;
            case MotionEvent.ACTION_MOVE:

                //判断是否已经滚动到了底部
                if (topScrollViewIsBottom) {
                    int dy = lastY - y;
                    //判断是否是向上滑动和是否在第一屏
                    if (dy > 0 && currPosition == 0) {
                        if (dy >= scaledTouchSlop) {
                            isIntercept = true;//拦截事件
                            lastY=y;
                            lastX=x;
                        }
                    }
                }

                if (bottomScrollVIewIsInTop) {
                    int dy = lastY - y;
                    //判断是否是向下滑动和是否在第二屏
                    if (dy < 0 && currPosition == 1) {
                        if (Math.abs(dy) >= scaledTouchSlop) {
                            if(PullUpToLoadMore.isTop){//如果viewpager里边的scrollview在最顶部，，就让外边的scrollview获取焦点，否则，让最里边的scrollview获取焦点
                                isIntercept = true;
                            }
                        }
                    }

                }else{
                    int dy = lastY - y;//上下滑动的距离
                    int dx = lastX - x;//左右滑动的距离

                    //判断是否是向上滑动和是否在第二屏   如果是在刚到第二屏的时候，向上滑动，也让父控件获取焦点
//                    在onInterceptTouchEvent（）方法中，如果返回true，父控件拦截事件，如果返回false，则向下传递
                    if (dy < 0 && currPosition == 1) {
                        if (Math.abs(dy) >= scaledTouchSlop) {
                            if(PullUpToLoadMore.isTop){//如果viewpager里边的scrollview在最顶部，，就让外边的scrollview获取焦点，否则，让最里边的scrollview获取焦点

                                //这里加一个判断，如果左右滑动的距离小于上下滑动的距离，我们认为用户在上下滑动
                                 //如果左右滑动的距离大于上下滑动的距离，我们认为用户在左右滑动
                                //上下滑动时，让父控件拦截事件
                                //左右滑动时，让子控件拦截事件


                                if(Math.abs(dy)>Math.abs(dx)){//上下滑动
                                    isIntercept = true;
                                }else{//左右滑动
                                    isIntercept = false;
                                }

                            }
                        }
                    }
                }
                break;
        }
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        int x = (int) event.getX();
        velocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int dy = lastY - y;
                if (getScrollY() + dy < 0) {
                    dy = getScrollY() + dy + Math.abs(getScrollY() + dy);
                }

                if (getScrollY() + dy + getHeight() > bottomScrollView.getBottom()) {
                    dy = dy - (getScrollY() + dy - (bottomScrollView.getBottom() - getHeight()));
                }
                scrollBy(0, dy);
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;

                velocityTracker.computeCurrentVelocity(1000);
                float yVelocity = velocityTracker.getYVelocity();

                if (currPosition == 0) {
                    if (yVelocity < 0 && yVelocity < -speed) {
                        smoothScroll(position1Y);
                        currPosition = 1;
                    } else {
                        smoothScroll(0);
                    }
                } else {
                    if (yVelocity > 0 && yVelocity > speed) {
                        smoothScroll(0);
                        currPosition = 0;
                    } else {
                        smoothScroll(position1Y);
                    }
                }
                break;
        }
        lastY = y;
        lastX = x;
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int childTop = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(l, childTop, r, childTop + child.getMeasuredHeight());
            childTop += child.getMeasuredHeight();
        }
    }



    //通过Scroller实现弹性滑动
    private void smoothScroll(int tartY) {
        int dy = tartY - getScrollY();
        scroller.startScroll(getScrollX(), getScrollY(), 0, dy);
        invalidate();
    }


    //滚动到顶部
    public void scrollToTop(){
        smoothScroll(0);
        currPosition=0;
        topScrollView.smoothScrollTo(0,0);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

}
