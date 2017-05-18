package com.qfdqc.views.pulltoloadmoreview.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by LQL on 2017/1/20.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mList;
    private ArrayList<String> mListString;
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context, ArrayList<Fragment>  mList, ArrayList<String> mListString) {
        super(fm);
        this.context = context;
        this.mList = mList;
        this.mListString = mListString;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListString.get(position);
    }
}
