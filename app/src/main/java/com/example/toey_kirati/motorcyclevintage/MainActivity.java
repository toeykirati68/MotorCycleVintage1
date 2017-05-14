package com.example.toey_kirati.motorcyclevintage;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    // Explicit
    private UserTABLE objUserTABLE;
    private BikeTABLE objBikeTABLE;
    private OrderTABLE objOrderTABLE;

    private EditText userEditText,passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial Widget
        initialWidget();

        //Connected SQLite
        connectedSQLite();

        synJSONtoSQLite();
    } // onCreate

    private void initialWidget() {
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
    }// initialWidget


    private void connectedSQLite() {
    }

    public void clickLogin(View view){
        String strUser = userEditText.getText().toString().trim();
        String strPassword = passwordEditText.getText().toString().trim();
        //check Zero
        if (strUser.equals("") ||strPassword.equals("")){
            //Have Space
            errorDialog("มีช่องว่าง","กรุณากรอกให้ครบ ทุกช่อง คะ");
        } else {
            //No Space
            checkUserPassword(strUser,strPassword);
        } //clickLogin

    private void synJSONtoSQLite (){
        StrictMode. ThreadPolicy MyPolicy = new StrictMode
                .ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(MyPolicy);
       //Loop 2 Times
        int intTimes = 0;
        while (intTimes <= 1){
            //Variable & Constant
            InputStream objInputStream = null;
            String strJSON = null;
            String strUserURL = "http://swiftcodingthai.com/23jul/get_data_master.php";
            String strBikeURL = "http://swiftcodingthai.com/23jul/get_data_bike.php";
            HttpPost objHttpPost;
            //1. Create InputStream
            try{
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes) {
                    case 0:
                        objHttpPost = new  HttpPost (strUserURL);
                        break;
                    default:
                        objHttpPost = new  HttpPost(strBikeURL);
                        break;
                }
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
            }catch (Exception e){
                Log.d("masterUNG","InputStream ==>" + e.toString());
            }

            //2. Create strJSON
            try {
                BufferedReader objBufferedReadder = new BufferedReader
                        (new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while ((strLine = objBufferedReadder.readLine()) != null) {
                    objStringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON = objStringBuilder.toString();
            } catch (Exception e) {
                Log.d("Error" , "strJSON ==>" + e.toString());

            }

            //3. Update to SQLite
            try {
                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length();i++) {
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                    switch (intTimes) {
                        case 0:
                            //Update userTABLE
                            String strUser = jsonObject.getString("User");
                            String strPassword = jsonObject.getString("Password");
                            String strName = jsonObject.getString("Name");
                            objUserTABLE.addNewUser(strUser,strPassword,strName);
                            break;
                        default:
                            //Update foodTABLE
                            String strBike = jsonObject.getString("Bike");
                            String strPrice = jsonObject.getString("Price");
                            objBikeTABLE.addNewBike(strBike,strPrice);
                            break;

                    }
                }
            } catch (Exception e){
                Log.d("masterUNG","Update SQLite ==> " + e.toString());
            }

            //Increase intTimes
         } intTimes += 1;
   }     // while

    private void checkUserPassword(String strUser,String strPassword){
        try {
            String [] strMyResult = objUserTABLE.searchUserPassword(strUser);
            if (strPassword.equals(strMyResult[2])){
                //Password True
                welcomeDialog();
            } else {
                //Password False
                errorDialog("Password False","Please Try Again Password False");
            }
        } catch (Exception e) {
            errorDialog("User False","ไม่มี" + strUser + "ใน ฐานข้อมูลของเรา");
        }
    } //checkUserPassword

    private void errorDialog(String strTitle, String strMessage){
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_question);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("ตกลง",new DialogInterface.OnClickListener() {
            @override
            public void onClick(DialogInterface dialogInterface,int i) {
                dialogInterface.dismiss();
            }
        });
            objBuilder.show();
} // errorfDialog

    private void welcomeDialog(final  String strName) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.Motorcycle);
        objBuilder.setTitle("Welcome");
        objBuilder.setMessage("ยินดีต้อนรับ" + strName + "\n" + "สู่ระบบของเรา");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent objIntent = new Intent(MainActivity.this, OrderActivity.class);
                objIntent.putExtra("office", strName);
                startActivity(objIntent);
                finish();
            }
                });
        objBuilder.show();
    } //welcomeDialog
}


