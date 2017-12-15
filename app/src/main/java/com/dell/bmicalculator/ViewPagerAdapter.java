package com.dell.bmicalculator;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dell on 9/16/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private Fragment[]fragments;
    private String[]pageTitle;
    private int[] tabIcons;

    public ViewPagerAdapter(Context context, FragmentManager fm , Fragment[]fragments, String[]pageTitle,int[]tabIcons) {
        super(fm);
        this.fragments = fragments;
        this.pageTitle = pageTitle;
        this.context = context;
        this.tabIcons = tabIcons;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab,null);
        TextView tabTitle = (TextView) view.findViewById(R.id.title);
        ImageView tabIcon = (ImageView)view.findViewById(R.id.icon);

        tabTitle.setText(pageTitle[position]);
        tabIcon.setImageResource(tabIcons[position]);


        return view;
    }


}
