package com.dell.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.IDNA;
import android.util.Log;

import java.util.ArrayList;



/**
 * Created by dell on 9/22/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bmi_database";
    private static final String DATABASE_TABLE_NAME = "bmi_table";
    private static final int DATABASE_VIRSION = 9;

    //culomns in table
    private static final String CULOMN_ID = "user_id";
    private static final String CULOMN_AGE = "user_age";
    private static final String CULOMN_SEX = "user_sex";
    private static final String CULOMN_HEIGHT = "user_height";
    private static final String CULOMN_WEIGHT = "user_weight";
    private static final String CULOMN_BMI_RESULT = "user_bmi_result";
    private static final String CULOMN_DATE = "test_date";

    private SQLiteDatabase db;
    private Cursor cursor;

    private static final String CREATE_DATABASE =
            "CREATE TABLE " + DATABASE_TABLE_NAME + "(" +
                    CULOMN_ID + " INTEGER PRIMARY KEY ," +
                    CULOMN_AGE + " VARCHAR," +
                    CULOMN_SEX + " VARCHAR," +
                    CULOMN_HEIGHT + " INTEGER," +
                    CULOMN_WEIGHT + " INTEGER," +
                    CULOMN_BMI_RESULT + " INTEGER," +
                    CULOMN_DATE + " DATETIME DEFULT CURRENT_TIMESTAMP);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VIRSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + DATABASE_TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void INSERT_INTO_BMI_TABLE(String age, String sex, int height, int weight, double bmi_result) {
        db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CULOMN_AGE, age);
        contentValues.put(CULOMN_SEX, sex);
        contentValues.put(CULOMN_HEIGHT, height);
        contentValues.put(CULOMN_WEIGHT, weight);
        contentValues.put(CULOMN_BMI_RESULT, bmi_result);



        db.insert(DATABASE_TABLE_NAME, null, contentValues);
        Log.d("inserted: ", "success");


    }

    public InfoModel GET_DATA(int id) {
        String SELECT_DATA = "SELECT * FROM " + DATABASE_TABLE_NAME + " where " + CULOMN_ID + "='" + id + "';";
        db = this.getReadableDatabase();
        cursor = db.rawQuery(SELECT_DATA, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

            InfoModel model = new InfoModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), String.valueOf(cursor.getInt(3)),
                    String.valueOf(cursor.getInt(4)), String.valueOf(cursor.getDouble(5)));

            return model;
    }

    public ArrayList<InfoModel> GET_ALL_DATA(){
        ArrayList<InfoModel> infoArray = new ArrayList<>();
        String SELECT_DATA = "SELECT * FROM " + DATABASE_TABLE_NAME ;
        db = this.getReadableDatabase();
        cursor = db.rawQuery(SELECT_DATA, null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false){
            InfoModel model = new InfoModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), String.valueOf(cursor.getInt(3)),
                    String.valueOf(cursor.getInt(4)), String.valueOf(cursor.getDouble(5)));
            infoArray.add(model);
            cursor.moveToNext();
        }

        return infoArray;


    }

    public int GET_LAST_ID(){
        String GET_LAST_AGE = "select "+CULOMN_ID+" from "+DATABASE_TABLE_NAME+" order by "+CULOMN_ID+" DESC LIMIT 1";
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_LAST_AGE,null);
        if (cursor != null)
            cursor.moveToFirst();
        int lastID = cursor.getInt(0);
        return lastID;
    }

    public void REMOVE_RECORD(int id){
        String DELETE_RECORD = "delete from "+DATABASE_TABLE_NAME+" where "+CULOMN_ID+ " = "+id;
        Log.d("from database helper: ",DELETE_RECORD);
        db.execSQL("delete from "+ DATABASE_TABLE_NAME +" where "+ CULOMN_ID +" = "+id);
        Log.d("from helper: ","success");
    }




}


























