package com.example.toey_kirati.motorcyclevintage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by TOEY_KIRATI on 2/5/2560.
 */

public class OrderTABLE {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSQLiteDatabase, readSqLiteDatabase;
    public OrderTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSQLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    } // Constructor
} // Main Class
