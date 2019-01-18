package com.qfdqc.views.pulltoloadmoreview.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.qfdqc.views.pulltoloadmoreview.R;
import com.qfdqc.views.pulltoloadmoreview.adapter.NetworkImageHolderView;
import com.qfdqc.views.pulltoloadmoreview.adapter.SimpleFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> list_fragment = new ArrayList<>();
    private ArrayList<String> list_title = new ArrayList<>();
    private OneFragment mOneFragment;
    private TwoFragment mTwoFragment;


    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;
    ConvenientBanner mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initControls();
    }


    /**
     * 初始化各控件
     */
    private void initControls() {
        List<String> networkImages = new ArrayList<>();
        networkImages.add("http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg");
        networkImages.add("http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg");
        networkImages.add("http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg");
        networkImages.add("http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg");
        mBanner = (ConvenientBanner) findViewById(R.id.banner_convenient);
        mBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages);
        //是否显示小圆点
        mBanner.setPointViewVisible(false);
        mBanner.startTurning(2000);

        //初始化各fragment
        mOneFragment = new OneFragment();
        mTwoFragment = new TwoFragment();
        list_fragment.add(mOneFragment);
        list_fragment.add(mTwoFragment);
        list_title.add("第一个页面");
        list_title.add("第二个页面");

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), list_fragment, list_title);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
