package com.dell.bmicalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.DataTruncation;
import java.util.ArrayList;

/**
 * Created by dell on 9/16/2017.
 */

public class HomeFragment extends Fragment  {

    private static final double LEAST_BMI_NORMA = 18.5;
    private static final double MOST_BMI_NORMA = 25;
    public static final String PREF_BMI = "PREF_BMI";
    public static final String BMI_EDITOR = "BMI_EDITOR";
    private static final String TAG = HomeFragment.class.getSimpleName();
    private TextView bmiResult,userInfo,bmiSummary,normalweightTxt;
    private ImageView expressionIcon;
    private String userWeight,userHeight,userAge,userSex,summary;
    private double bmi,leastWeight,mostWeight;
    private SharedPreferences prefBmi, pref_Age_Sex,pref_height_weight;
    private SharedPreferences.Editor ageEditor;

    private DatabaseHelper databaseHelper;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frag, container, false);
        bmiResult = (TextView) view.findViewById(R.id.bmi_result);
        userInfo = (TextView) view.findViewById(R.id.user_info);
        bmiSummary = (TextView) view.findViewById(R.id.bmi_summary);
        normalweightTxt = (TextView) view.findViewById(R.id.noraml_weight_txt);
        expressionIcon = (ImageView) view.findViewById(R.id.expression_icon);

        pref_height_weight = getContext().getSharedPreferences(HeightWeightFragment.PREF_HEIGHT_WEIGHT, Context.MODE_PRIVATE);
        pref_Age_Sex = getContext().getSharedPreferences(AgeFragment.PREF_USER_AGE_SEX, Context.MODE_PRIVATE);
        if (new PrefManager(getContext()).isFirstTime_home() == false) {
            Log.d("display from: ","sharedpref");
            Log.d("display from: ",pref_Age_Sex.getString(AgeFragment.USER_AGE_EDITOR,""));
            // get data from shared preference
            userAge = pref_Age_Sex.getString(AgeFragment.USER_AGE_EDITOR, "");
            userSex = pref_Age_Sex.getString(AgeFragment.USER_SEX_EDITOR, "");
            userHeight = pref_height_weight.getString(HeightWeightFragment.HEIGHT_EDITOR, "");
            userWeight = pref_height_weight.getString(HeightWeightFragment.WEIGHT_EDITOR, "");

            set_result(userAge,userSex,userHeight,userWeight);
            new PrefManager(getContext()).writeInPref2();

        }else {
            // get data from database
            Log.d("display from: ","database");
            GET_LAST_RECORD_FROM_DATABASE();
        }
            return view;

    }




    public void set_result(String userAge,String userSex, String userHeight,String userWeight){
        //calculate bmi = weight / (height in meters)^2
        bmi = Math.round(Float.parseFloat(userWeight) / Math.pow(Float.parseFloat(userHeight) / 100, 2));
        //set results
        bmiResult.setText(bmi + "");
        userInfo.setText(userAge + " Y | " + userWeight + " Kg | " + userHeight + " Cm");
        BMI_CLASSIFY(bmi);
        GET_NORMAL_WEIGHT(userHeight);
        databaseHelper = new DatabaseHelper(getContext());
        databaseHelper.INSERT_INTO_BMI_TABLE(userAge,userSex,Integer.parseInt(userHeight)
        ,Integer.parseInt(userWeight),bmi);

    }
    private void GET_LAST_RECORD_FROM_DATABASE() {
        databaseHelper = new DatabaseHelper(getContext());
        int lastID = databaseHelper.GET_LAST_ID();
        InfoModel model =  databaseHelper.GET_DATA(lastID);
        bmiResult.setText(model.getBmiResult());
        userInfo.setText(model.getAge() + " Y | " + model.getWeight() + " Kg | " + model.getHeight() + " Cm");
        BMI_CLASSIFY(Double.parseDouble(model.getBmiResult()));
        GET_NORMAL_WEIGHT(model.getHeight());

    }

    public void BMI_CLASSIFY(double bmi){
        //classification bmi
        if (bmi < 18.5) {
            //Underweight
            bmiResult.setTextColor(Color.BLUE);
            expressionIcon.setImageResource(R.drawable.ic_expressions_blue);
            normalweightTxt.setTextColor(Color.BLUE);
            summary = "Underweight";

        } else if (bmi < 25) {
            //normal
            bmiResult.setTextColor(Color.GREEN);
            expressionIcon.setImageResource(R.drawable.ic_expressions_green);
            normalweightTxt.setTextColor(Color.GREEN);
            summary = "Normal";

        } else if (bmi < 30) {
            //Overweight
            bmiResult.setTextColor(Color.rgb(255, 140, 0));
            expressionIcon.setImageResource(R.drawable.ic_expressions_yellow);
            normalweightTxt.setTextColor(Color.rgb(255, 140, 0));
            summary = "Overweight";

        } else {
            //Obese
            bmiResult.setTextColor(Color.RED);
            expressionIcon.setImageResource(R.drawable.ic_expressions_red);
            normalweightTxt.setTextColor(Color.RED);
            summary = "Obese";
        }

        bmiSummary.setText("Your BMI is " + summary);
        normalweightTxt.setText(leastWeight + " - " + mostWeight);
    }

    public void GET_NORMAL_WEIGHT(String userHeight){
        //calculate normal weight according to user height
        leastWeight = Math.round(LEAST_BMI_NORMA * Math.pow(Float.parseFloat(userHeight) / 100, 2));
        mostWeight = Math.round(MOST_BMI_NORMA * Math.pow(Float.parseFloat(userHeight) / 100, 2));
        normalweightTxt.setText(leastWeight+" - "+mostWeight);
    }






























    }







