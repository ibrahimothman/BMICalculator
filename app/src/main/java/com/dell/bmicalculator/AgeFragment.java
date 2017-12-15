package com.dell.bmicalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.github.paolorotolo.appintro.ISlidePolicy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dell on 9/13/2017.
 */

public class AgeFragment extends Fragment implements ISlidePolicy {
    public static final String PREF_USER_AGE_SEX = "PREF_USER_AGE_SEX";
    public static final String USER_AGE_EDITOR = "USER_AGE_EDITOR";
    public static final String USER_SEX_EDITOR = "USER_SEX_EDITOR";

    private String userAge;
    private String userSex;

    private EditText ageLabel;
    private RadioButton radioMale;
    private RadioButton radioFemale;

    private SharedPreferences pref;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.age_frag , container , false);

        ageLabel = (EditText) view.findViewById(R.id.age_label);
        radioMale = (RadioButton) view.findViewById(R.id.redio_male);
        radioFemale = (RadioButton) view.findViewById(R.id.redio_female);



        return view;
    }


    @Override
    public boolean isPolicyRespected() {
        userAge = ageLabel.getText().toString();
        userSex = (radioMale.isChecked())? "male" : (radioFemale.isChecked())? "female" :"";
      //  setSex.add(userSex);
        pref = getActivity().getSharedPreferences(PREF_USER_AGE_SEX, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(USER_AGE_EDITOR,userAge);
        editor.putString(USER_SEX_EDITOR,userSex);
        editor.commit();

        return userAge.length()>0 && (radioMale.isChecked()|| radioFemale.isChecked()) ;
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {
        if (userAge.length() == 0) {
            Toast.makeText(getActivity(), "please enter your age", Toast.LENGTH_SHORT).show();
        }else if (userSex == ""){
            Toast.makeText(getActivity(), "please choose your sex", Toast.LENGTH_SHORT).show();
        }
    }


}
