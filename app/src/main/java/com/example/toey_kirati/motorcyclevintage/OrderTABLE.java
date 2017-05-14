package com.example.toey_kirati.motorcyclevintage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by TOEY_KIRATI on 2/5/2560.
 */

public class OrderTABLE {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSQLiteDatabase, readSqLiteDatabase;

    public static final String ORDER_TABLE = "orderTABLE";
    public static final String COLUMN_ID_ORDER = "_id";
    public static final String COLUMN_OFFICER = "officer";
    public static final String COLUMN_BIKE= "Bike";
    public static final String COLUMN_ITEM = "Item";

    public OrderTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSQLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    } // Constructor

    public String[] readAllOrder(int intChoose){

        String[] strReadAll = null;
        Cursor objCursor = readSqLiteDatabase.query(ORDER_TABLE,
                new String[]{COLUMN_ID_ORDER, COLUMN_BIKE, COLUMN_ITEM},
                null, null, null, null, null);
        if (objCursor != null){
            objCursor.moveToFirst();
            strReadAll = new String[objCursor.getCount()];
            for (int i = 0; i < objCursor.getCount(); i++){
                if (intChoose == 1) {
                    strReadAll[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_BIKE));
                } else {
                    strReadAll[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ITEM));
                }  //if
                objCursor.moveToNext();
            }   // for
        }   //if
        objCursor.close();
        return  strReadAll;
    }   // readAllOrder

    public long addOrder (String strOfficer,String strDesk,String strBike,String strItem) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_OFFICER, strOfficer);
        objContentValues.put(COLUMN_BIKE, strBike);
        objContentValues.put(COLUMN_ITEM, strItem);
        return readSqLiteDatabase.insert(ORDER_TABLE, null, objContentValues);
    }

} // Main Class
