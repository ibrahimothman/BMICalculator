package com.dell.bmicalculator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by dell on 9/17/2017.
 */

public class CustomViewPager extends ViewPager {



    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    //to disable swipping using finger

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

}
