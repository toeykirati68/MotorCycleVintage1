package com.example.toey_kirati.motorcyclevintage;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by TOEY_KIRATI on 8/5/2560.
 */

public class MyAdapter extends BaseAdapter {

    //Explicit
    private Context objContext;
    private String[] bikeStrings, priceStrings;


    public MyAdapter(Context objContext,String[] bikeStrings,String[] priceStrings) {
        this.objContext = objContext;
        this.bikeStrings = bikeStrings;
        this.priceStrings = priceStrings;
    }

    @Override
    public int getCount() {

        return bikeStrings.length;
    }

    @Override
    public Object getItem(int i) {

        return null;
    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    @Override
    public View getView(int i, View View, ViewGroup viewGroup) {
        LayoutInflater objLayoutInflater = (LayoutInflater) objContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = objLayoutInflater.inflate(R.layout.my_listview,viewGroup,false);
        //Show Bike
        TextView bikeTextView = (TextView)view1.findViewById(R.id.txtListBike);
        bikeTextView.setText(bikeStrings[i]);
        //Show Price
        TextView priceTextView = (TextView) view1.findViewById(R.id.txtListPrice);
        priceTextView.setText(priceStrings[i]);
        //Show ImageBike
        ImageView bikeImageView = (ImageView) view1.findViewById(R.id.imvListBike);
        Picasso.with(objContext).load(sourceString[i]).into(bikeImageView);
        return view1;
    } //getView
} // Main Class

