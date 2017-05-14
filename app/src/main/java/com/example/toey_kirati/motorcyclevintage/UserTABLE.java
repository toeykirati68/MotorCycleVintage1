package com.example.toey_kirati.motorcyclevintage;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by TOEY_KIRATI on 2/5/2560.
 */

public class UserTABLE {

    //Explicit
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String USER_TABLE = "userTABLE";
    public static final String COLUMN_ID_USER = "_id";
    public static final String COLUMN_USER = "User";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_NAME = "Name";

    public UserTABLE(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();

    } // Constructor

    public String[] searchUserPassword(String strUser){
        try{
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(USER_TABLE,
                    new String[] {COLUMN_ID_USER,COLUMN_USER,COLUMN_PASSWORD,COLUMN_NAME},
                    COLUMN_USER + "=?",
                    new String[]{String.valueOf(strUser)},
                    null,null,null,null);
            if (objCursor !=null){
                if (objCursor.moveToFirst()){
                    strResult = new String[4];
                    strResult [0] = objCursor.getString(0);
                    strResult [1] = objCursor.getString(1);
                    strResult [2] = objCursor.getString(2);
                    strResult [3] = objCursor.getString(3);
                }
            }
            objCursor.close();
            return strResult;
        } catch (Exception e) {
            return null;
        }

        //return new String[0];
    } //searchUserPassword


    public long addNewUser (String strUser,String strPassword,String strName){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER,strUser) ;
        objContentValues.put(COLUMN_PASSWORD,strPassword);
        objContentValues.put(COLUMN_NAME,strName);
        return readSqLiteDatabase.insert(USER_TABLE,null,objContentValues);
    }

    private void checkUserPassword(String strUser,String strPassword){
                try {
                    String[] strMyResult = objUserTABLE.searchUserPassword(strUser);
                    if (strPassword.equals(strMyResult[2])){
                    //PassWord True
                    welcomeDialog();
                    } else {
                    //Password False
                    errorDialog("Password False","Please Try Again Password False");
                }
            } catch (Exception e) {
                errorDialog("User False","ไม่มี"+strUser+"ใน ฐานข้อมูลของเรา");
            }

        } // checkUserPassword

        private void welcomeDialog(String strName){
            AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
            objBuilder.setIcon(R.drawable.bike);
            objBuilder.setTitle("Welcome");
            objBuilder.setMessage("ยินดีต้อนรับ"+strName+"\n"+"สู่ระบบของเรา");
            objBuilder.setCancelable(false);
            objBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i){
                    dialogInterface.dismiss();
        }
    });
    objBuilder.show();
    //welcomeDialog


                public void clickLogin(View view){
        String strUser = userEditText.getText().toString().trim();
        String strPassword = passwordEditText.getText().toString().trim();
        //Check Zero
        if (strUser.equals("")|| strPassword.equals("")){
            //Have Space
            errorDialog("มีช่องว่าง","กรุณากรอกให้ครบ ทุกช่อง ครับ");
        } else{
            //No Space
            checkUserPassword(strUser,strPassword);
        }
    }}

} // Main Class
