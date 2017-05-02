package com.example.toey_kirati.motorcyclevintage;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TOEY_KIRATI on 2/5/2560.
 */

public class UserTABLE {

    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteOpenHelper writeSqLiteDatabase, readSqLiteDatabase;


    public UserTABLE(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();


    } // Constructor

} // Main Class
