package com.qfdqc.views.pulltoloadmoreview.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by baoyunlong on 16/6/8.
 */
public class MyScrollView extends ScrollView {
    private static String TAG = MyScrollView.class.getName();

    public void setScrollListener(ScrollListener scrollListener) {
        this.mScrollListener = scrollListener;
    }

    private ScrollListener mScrollListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mScrollListener != null) {
                    int contentHeight = getChildAt(0).getHeight();
                    int scrollHeight = getHeight();
                    int scrollY = getScrollY();
                    Log.d(TAG, "scrollY:" + scrollY + "contentHeight:" + contentHeight + " scrollHeight" + scrollHeight + "object:" + this);
                    mScrollListener.onScroll(scrollY);
                    if (scrollY + scrollHeight >= contentHeight || contentHeight <= scrollHeight) {
                        mScrollListener.onScrollToBottom();
                    } else {
                        mScrollListener.notBottom();
                    }
                    if (scrollY == 0) {
                        mScrollListener.onScrollToTop();
                    }
                }
                break;
            default:
                break;
        }
        boolean result = super.onTouchEvent(ev);
        requestDisallowInterceptTouchEvent(false);
        return result;
    }

    public interface ScrollListener {
        /**
         * 是否滑动到了底部
         */
        void onScrollToBottom();

        /**
         * 是都滑动到了顶部
         */
        void onScrollToTop();

        /**
         * 滑动距离
         *
         * @param scrollY
         */
        void onScroll(int scrollY);

        /**
         * 不在底部
         */
        void notBottom();
    }
}
