package com.qfdqc.views.pulltoloadmoreview.adapter;
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
    public SimpleFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> mList, ArrayList<String> mListString) {
        super(fm);
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
