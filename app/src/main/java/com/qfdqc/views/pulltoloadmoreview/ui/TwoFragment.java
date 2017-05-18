package com.qfdqc.views.pulltoloadmoreview.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qfdqc.views.pulltoloadmoreview.utils.MyScrollView;
import com.qfdqc.views.pulltoloadmoreview.utils.PublicStaticClass;
import com.qfdqc.views.pulltoloadmoreview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {
    View  mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_two, container, false);
        initView();
        return mView;
    }

    private void initView() {
        MyScrollView twoScrollView= (MyScrollView) mView.findViewById(R.id.twoScrollview);
        twoScrollView.setScrollListener(new MyScrollView.ScrollListener() {
            @Override
            public void onScrollToBottom() {

            }

            @Override
            public void onScrollToTop() {

            }

            @Override
            public void onScroll(int scrollY) {
                if (scrollY == 0) {
                    PublicStaticClass.IsTop = true;
                } else {
                    PublicStaticClass.IsTop = false;
                }
            }

            @Override
            public void notBottom() {

            }

        });
    }

}
