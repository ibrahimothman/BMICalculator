package com.dell.bmicalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dell on 9/16/2017.
 */

public class SettingFragment extends Fragment  {


    private EditText ageEdit,weightEdit,heightEdit;
    private Button cancel_btn,cal_btn;
    private String userAge,userSex,userHeight,userWeight;
    private RadioButton radioMale,radioFemale;

    private ViewPager viewPager;

    private DatabaseHelper databaseHelper;
    private int lastID;
    InfoModel model;
    private double bmi_result;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_frag , container , false);
        viewPager = (ViewPager)getActivity().findViewById(R.id.custom_viewpager);
        ageEdit = (EditText)view.findViewById(R.id.user_age_setting);
        weightEdit = (EditText)view.findViewById(R.id.user_weight_setting);
        heightEdit = (EditText)view.findViewById(R.id.user_height_setting);
        radioMale = (RadioButton) view.findViewById(R.id.radio_male_setting);
        radioFemale = (RadioButton) view.findViewById(R.id.radio_female_setting);
        cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        cal_btn = (Button) view.findViewById(R.id.calculate_btn);

        // change value in pref to display data from database not from shared pref
        SharedPreferences pref_age = getContext().getSharedPreferences(AgeFragment.PREF_USER_AGE_SEX,Context.MODE_PRIVATE);
        final SharedPreferences.Editor ageEditor = pref_age.edit();
        ageEditor.putString(AgeFragment.USER_AGE_EDITOR,"null");
        ageEditor.commit();

        // get data from database and set the into editTexts
        databaseHelper = new DatabaseHelper(getContext());
        lastID = databaseHelper.GET_LAST_ID();
        model = databaseHelper.GET_DATA(lastID);
        ageEdit.setText(model.getAge());
        weightEdit.setText(model.getWeight());
        heightEdit.setText(model.getHeight());
        if (model.getSex().equals("male")){
            radioMale.setButtonDrawable(R.drawable.ic_male_checked);
            radioFemale.setButtonDrawable(R.drawable.ic_female_unchecked);
        }else {
            radioMale.setButtonDrawable(R.drawable.ic_male_unchecked);
            radioFemale.setButtonDrawable(R.drawable.ic_female_checked);

        }

        //disable calculate button if data is missing

       ageEdit.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ageEdit.getText().length()==0){
                    cal_btn.setEnabled(false);
                }else {
                    cal_btn.setEnabled(true);
                }
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });

        heightEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (heightEdit.getText().length()==0){
                    cal_btn.setEnabled(false);
                }else {
                    cal_btn.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        weightEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (weightEdit.getText().length()==0){
                    cal_btn.setEnabled(false);
                }else {
                    cal_btn.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // if calculate button is clicked put data into pref and do to home tab
        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAge = ageEdit.getText().toString();
                userSex = (radioMale.isChecked())?"male":(radioFemale.isChecked())?"female":"";
                userHeight = heightEdit.getText().toString();
                userWeight = weightEdit.getText().toString();
                // calculate bmi
                bmi_result = Math.round(Float.parseFloat(userWeight) / Math.pow(Float.parseFloat(userHeight) / 100, 2));
                databaseHelper = new DatabaseHelper(getContext());
                databaseHelper.INSERT_INTO_BMI_TABLE(userAge,userSex,Integer.parseInt(userHeight),
                        Integer.parseInt(userWeight),bmi_result);
                viewPager.setCurrentItem(0);

            }
        });

        //cancel button
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to home tab
                viewPager.setCurrentItem(0);
            }
        });
        return view;
    }


}
