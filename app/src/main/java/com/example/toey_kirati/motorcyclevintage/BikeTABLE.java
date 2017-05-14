package com.example.toey_kirati.motorcyclevintage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TOEY_KIRATI on 2/5/2560.
 */

public class BikeTABLE {
    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase,readSQLiteDatabase;

    public static final String BIKE_TABLE = "bikeTABLE";
    public static final String COLUMN_ID_BIKE = "_id";
    public static final String COLUMN_BIKE = "Bike";
    public static final String COLUMN_PRICE = "Price";


    public BikeTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSQLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();

    } // Constructor
    public long addNewBike (String strBike,String strPrice) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_BIKE, strBike);
        objContentValues.put(COLUMN_PRICE, strPrice);
        return readSQLiteDatabase.insert(BIKE_TABLE, null, objContentValues);
    } // addNewBike

    public String[] readAllBike(int intColumn) {
        String[] strReadAll = null;
        Cursor objCursor = readSQLiteDatabase.query(BIKE_TABLE,
                new String []{COLUMN_ID_BIKE,COLUMN_BIKE,COLUMN_PRICE},
        null, null, null, null, null);
        if (objCursor != null) {
        objCursor.moveToFirst();
            strReadAll = new String[objCursor.getCount()];
            for (int i = 0; i<= objCursor.getCount();i++){
                switch (intColumn) {
                    case 1:
                        strReadAll[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_BIKE));
                        break;
                    default:
                        strReadAll[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_PRICE));
                        break;
                }
                objCursor.moveToNext();
            }
        }
        return strReadAll;

        }

} // Main Class
