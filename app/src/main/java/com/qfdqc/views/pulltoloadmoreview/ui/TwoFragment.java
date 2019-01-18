package com.qfdqc.views.pulltoloadmoreview.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.qfdqc.views.pulltoloadmoreview.adapter.RecyclerAdapter;
import com.qfdqc.views.pulltoloadmoreview.R;
import com.qfdqc.views.pulltoloadmoreview.utils.PullUpToLoadMore;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {
    View mView;
    RecyclerView mRecyclerView;
    RecyclerAdapter mRecyclerAdapter;
    ArrayList<String> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        for (int i = 0; i < 20; i++) {
            mList.add("我是李小米吖：" + i);
        }
        mView = inflater.inflate(R.layout.fragment_two, container, false);
        initView();
        return mView;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerAdapter = new RecyclerAdapter(getActivity());
        mRecyclerAdapter.setList(mList);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        //兼容api23以以下版本
        mRecyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
                //得到当前界面，第一个子视图的position
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findFirstVisibleItemPosition();
                if (firstVisibleItemPosition == 0) {
                    PullUpToLoadMore.bottomChildViewIsTop = true;
                } else {
                    PullUpToLoadMore.bottomChildViewIsTop = false;
                }
            }
        });
    }

}
