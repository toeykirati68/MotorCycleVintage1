package com.example.toey_kirati.motorcyclevintage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TOEY_KIRATI on 2/5/2560.
 */

public class BikeTABLE {
    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase,readSQLiteDatabase;
    public bikeTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSQLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();

    } // Constructor
} // Main Class
