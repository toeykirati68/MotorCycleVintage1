package com.example.toey_kirati.motorcyclevintage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by TOEY_KIRATI on 12/5/2560.
 */

public class OrderAdapter extends BaseAdapter {
    private Context objContext;
    private String[] bikeStrings, itemStrings;

    public OrderAdapter(Context objContext, String[] bikeStrings, String[] itemStrings){
        this.objContext = objContext;
        this.bikeStrings = bikeStrings;
        this.itemStrings = itemStrings;
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
        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = objLayoutInflater.inflate(R.layout.order_listview, viewGroup, false);
        // Bike
        TextView bikeTextView = (TextView) view1.findViewById(R.id.txtBikeConfirm);
        bikeTextView.setText(bikeStrings[i]);
        // item
        TextView itemTextView = (TextView) view1.findViewById(R.id.txtitemConfirm);
        itemTextView.setText(itemStrings[i]);
        return view1;
    }   //getView+
} //Main Class
