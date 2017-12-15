package com.dell.bmicalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by dell on 9/16/2017.
 */

public class HistoryFragment extends Fragment  {

    private static final String TAG = HistoryFragment.class.getSimpleName();
    SharedPreferences pref_age_sex, pref_height_weight,pref_bmi;
    private String userAge, userSex, userHeight, userWeight,bmiResult;
    private RecyclerView recyclerView;
    private ArrayList<InfoModel> infoModelArrayList = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    HistoryAdapter historyAdapter;






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_frag, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.history_list);
        databaseHelper = new DatabaseHelper(getContext());
        infoModelArrayList = databaseHelper.GET_ALL_DATA();
//        Log.d("from history frag: ",infoModelArrayList.size()+"");
//        Log.d("from history frag: ",infoModelArrayList.get(0).getAge()+"");
//        Log.d("from history frag: ",infoModelArrayList.get(1).getAge()+"");
//        Log.d("from history frag: ",infoModelArrayList.get(2).getAge()+"");
        historyAdapter = new HistoryAdapter(getContext(),infoModelArrayList);
        recyclerView.setAdapter(historyAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            infoModelArrayList.clear();
            Log.d("from history frag: ","iam in visible");
            databaseHelper = new DatabaseHelper(getContext());
            infoModelArrayList = databaseHelper.GET_ALL_DATA();
            historyAdapter = new HistoryAdapter(getContext(),infoModelArrayList);
            recyclerView.setAdapter(historyAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
//            historyAdapter.notifyDataSetChanged();
        }

    }


}


