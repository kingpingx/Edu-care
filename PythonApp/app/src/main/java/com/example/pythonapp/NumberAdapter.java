package com.example.pythonapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NumberAdapter extends ArrayAdapter {

    private String[] numberNames;

    private Integer[] imageid2;
    private Activity context2;
    public NumberAdapter(Activity context, String[] numberNames, Integer[] imageid1) {
        super(context, R.layout.number_list, numberNames);
        this.context2 = context;
        this.numberNames = numberNames;
        this.imageid2 = imageid1;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context2.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.number_list, null, true);
        TextView textViewCountry = row.findViewById(R.id.textViewNumber);

        ImageView imageFlag = row.findViewById(R.id.imageViewNumber);

        textViewCountry.setText(numberNames[position]);
        imageFlag.setImageResource(imageid2[position]);
        return  row;
    }

}
