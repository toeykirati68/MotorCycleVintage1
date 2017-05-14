package com.example.toey_kirati.motorcyclevintage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by TOEY_KIRATI on 8/5/2560.
 */

public class OrderActivity extends AppCompatActivity {
    // Explicit
    private TextView officerTextView;
    private Spinner deskSpinner;
    private ListView bikeListView;
    private String OfficerString, deskString, bikeString, itemString;

    @Override
    protected void onCreatel(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        //Bind Widget
        bindWidget();
        //Show officer & Desk
        showOfficerAndDesk();
        //Show Spinner
        showSpinner();
        //Create ListView
        createListView();
    } // onCreate

    public void clickOrder(View view){
        Intent objIntent = new Intent(OrderActivity.this, ConfimOrderActivity.class);
        objIntent.putExtra("Officer", officerString);
        objIntent.putExtra("Desk", deskString);
        startActivity(objIntent);
    }


    private void createListView() {
        BikeTABLE objBikeTABLE = new BikeTABLE(this);
        final String[] strBike = objBikeTABLE.readAllBike(1);
        String[] strPrice = objBikeTABLE.readAllBike(2);
        MyAdapter objMyAdapter = new MyAdapter(OrderActivity.this, strBike, strPrice);
        bikeListView.setAdapter(objMyAdapter);

        bikeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long 1) {
                bikeString = strBike[i];
                chooseItem();
            }
        });

    }  // createListView

    private void showOfficerAndDesk() {
        officerString = getIntent().getStringExtra("Officer");
        deskString = getIntent().getStringExtra("Desk");
        officerTextView.setText(officerString);
        deskTextView.setText(deskString);
    }

    private void showSpinner() {
        // Create Spinner
        final String[] strDeskSpinner = {"One", "Two", "Three", "Four", "Five"};
        ArrayAdapter<String> deskAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strDeskSpinner);
        deskSpinner.setAdapter(deskAdapter);
        // Active onClice
        deskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long 1) {
                strDesk = strDeskSpinner[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                strDesk = strDeskSpinner[0];
            }
        });
    } // showSpinner

    private void chooseItem() {
        final CharSequence[] choiceCharSequences = {"1 set", "2set", "3set", "4set", "5set"};
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle(bikeString);
        objBuilder.setSingleChoiceItems(choiceCharSequences, -1, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                switch (i){
                    case 0:
                        itemString = "1";
                        break;
                    case 1:
                        itemString = "2";
                        break;
                    case 2:
                        itemString = "3";
                        break;
                    case 3:
                        itemString = "4";
                        break;
                    case 4:
                        itemString = "5";
                        break;
                } //Swich
                uploadToSQLite();
                dialogInterface.dismiss();
            } //event
        });
    } //chooseItem

    private void uploadToSQLite(){

    } // uploadToSQLite

    private void bindWidget () {
        officerTextView = (TextView) findViewById(R.id.textView2);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        bikeListView = (ListView) findViewById(R.id.listView);
        officerTextView = (TextView) findViewById(R.id.textView3);
        deskTextView = (TextView) findViewById(R.id.textView4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) (...)

    @Override
    public boolean onOptionsItemSelected(MenuItem item) (...)

} // Main Class
