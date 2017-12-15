package com.dell.bmicalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.paolorotolo.appintro.ISlidePolicy;

/**
 * Created by dell on 9/13/2017.
 */

public class HeightWeightFragment extends Fragment implements ISlidePolicy {
    public static final String PREF_HEIGHT_WEIGHT = "PREF_HEIGHT_WEIGHT";
    public static final String HEIGHT_EDITOR = "HEIGHT_EDITOR";
    public static final String WEIGHT_EDITOR = "WEIGHT_EDITOR";
    private EditText height,weight;
    private String userHeight,userWeight;
    private SharedPreferences pref;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weight_height_frag , container , false);
        height = (EditText) view.findViewById(R.id.height);
        weight = (EditText) view.findViewById(R.id.weight);

        return view;
    }

    @Override
    public boolean isPolicyRespected() {
        userHeight = height.getText().toString();
        userWeight = weight.getText().toString();

        pref = getActivity().getSharedPreferences(PREF_HEIGHT_WEIGHT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(HEIGHT_EDITOR,userHeight);
        editor.putString(WEIGHT_EDITOR,userWeight);
        editor.commit();

        return userHeight.length()>0 && userWeight.length()>0;

    }

    @Override
    public void onUserIllegallyRequestedNextPage() {
        if (userWeight.length() == 0){
            Toast.makeText(getActivity(), "please enter your weight", Toast.LENGTH_SHORT).show();
        }else if (userHeight.length() == 0){
            Toast.makeText(getActivity(), "please enter your height", Toast.LENGTH_SHORT).show();
        }

    }
}
