package com.dell.bmicalculator;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dell on 9/14/2017.
 */

public class PrefManager {
    public static final String APP_IS_FIRST_TIME_PREF = "APP_IS_FIRST_TIME_PREF";
    public static final String IS_FIRIST_TIME_EDITOR = "IS_FIRIST_TIME_EDITOR";
    public static final String HOME_FRAGMENT_FOR_FIRST_TIME_PREF = "HOME_FRAGMENT_FOR_FIRST_TIME_PREF";
    public static final String HOME_FRAGMENT_FOR_FIRST_TIME_EDITOR = "HOME_FRAGMENT_FOR_FIRST_TIME_EDITOR";
    private SharedPreferences pref1,pref2;
    private Context context;
    public PrefManager(Context context) {
        this.context = context;
        pref1 = context.getSharedPreferences(APP_IS_FIRST_TIME_PREF,Context.MODE_PRIVATE);
        pref2 = context.getSharedPreferences(HOME_FRAGMENT_FOR_FIRST_TIME_PREF,Context.MODE_PRIVATE);
    }

    public void writeInPref1(){
        SharedPreferences.Editor editor1 = pref1.edit();
        editor1.putString(IS_FIRIST_TIME_EDITOR,"it not first time");
        editor1.commit();
    }

    public void writeInPref2(){
        SharedPreferences.Editor editor2 = pref2.edit();
        editor2.putString(HOME_FRAGMENT_FOR_FIRST_TIME_EDITOR,"it not first time");
        editor2.commit();
    }



    public boolean isFirstTime_app(){
        boolean status = false;

        if (pref1.getString(IS_FIRIST_TIME_EDITOR,"null").equals("null")) {

            status = false;
        }else {
            status = true;
        }
        return status;
        }

    public boolean isFirstTime_home(){
        boolean status = false;

        if (pref2.getString(HOME_FRAGMENT_FOR_FIRST_TIME_EDITOR,"null").equals("null")) {

            status = false;
        }else {
            status = true;
        }
        return status;
    }
    }



