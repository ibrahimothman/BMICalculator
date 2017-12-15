package com.dell.bmicalculator;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro2;

public class MainActivity extends AppIntro2{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().hide();

        if (new PrefManager(this).isFirstTime_app()) {
            Intent intent  = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
        }

        addSlide(new StartFragment());
        addSlide(new AgeFragment());
        addSlide(new HeightWeightFragment());


        showSkipButton(false);
        setVibrate(true);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Toast.makeText(this, "thank you for your visit", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent  = new Intent(this,HomeActivity.class);
        startActivity(intent);
        new PrefManager(this).writeInPref1();
        finish();

    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
