package com.example.toey_kirati.motorcyclevintage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by TOEY_KIRATI on 12/5/2560.
 */

public class ConfimOrderActivity extends AppCompatActivity {
    private TextView officerTextView, deskTextView;
    private  String officerString, deskString;
    private ListView orderListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        //Bind Widget
        bindWidget();
        //Show Officer & Desk
        showOfficerAndDesk();
    }   // onCreate

    private void bindWidget(){
        officerTextView = (TextView) findViewById(R.id.textView3);
        deskTextView = (TextView) findViewById(R.id.textView4);
        orderListView = (ListView) findViewById(R.id.listView2);
    }

    private void createListView(){
        OrderTABLE objOrderTABLE = new OrderTABLE(this);
        String[] strBike = objOrderTABLE.readAllOrder(1);
        String[] strItem = objOrderTABLE.readAllOrder(2);
        OrderAdapter objOrderAdapter = new OrderAdapter(ConfirmOrderActivity.this, strBike, strItem);
        orderListView.setAdapter(objOrderAdapter);
    }   // createListview
}
