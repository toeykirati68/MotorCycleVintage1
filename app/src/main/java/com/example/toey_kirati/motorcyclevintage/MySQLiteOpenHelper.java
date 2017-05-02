package com.example.toey_kirati.motorcyclevintage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TOEY_KIRATI on 2/5/2560.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    //Explicit
    private static final String DATABASE_NAME = "MotorCycle.db";
    private static final int DATABASE_VERSION = 1 ;
    private static final String CREATE_USER_TABLE = "create table userTABLE" +
            "(_id integer primary key,User text, Password text, Name text);";
    private static final String CREATE_BIKE_TABLE = "create table bikeTABLE" +
            "(_id integer primary key,Bike text, Source text, Price text);";
    private static final String CREATE_ORDER_TABLE = "create table orderTABLE" +
            "(_id integer primary key,Officer text,Desk text,Bike text,Item text);";

    public MySQLiteOpenHelper (Context context){
        super(context ,DATABASE_NAME,null,DATABASE_VERSION);
    }// constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_BIKE_TABLE);
        sqLiteDatabase.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
} // Main Class
