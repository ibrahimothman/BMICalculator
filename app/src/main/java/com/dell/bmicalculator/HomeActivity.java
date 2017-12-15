package com.dell.bmicalculator;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private Fragment[]fragments = {new HomeFragment(),new HistoryFragment(),new SettingFragment()};
    private String[]pageTitle = {"Home","History","Setting"};
    private int[]tabIcons = {R.drawable.ic_home_selector,R.drawable.ic_history_selector,R.drawable.ic_settings_selector};
    private ViewPagerAdapter mViewPagerAdapter;

    private CustomViewPager mViewPager;
    private TabLayout mTabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mViewPager = (CustomViewPager) findViewById(R.id.custom_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);


        mViewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager(), fragments, pageTitle, tabIcons);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        //get custom tabs
        for (int i = 0; i < mViewPagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mViewPagerAdapter.getTabView(i));
            }

        }






    }
}
