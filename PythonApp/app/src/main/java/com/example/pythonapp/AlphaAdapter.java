package com.example.pythonapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlphaAdapter extends ArrayAdapter {
    private String[] alphaNames;
    private String[] objectNames1;
    private Integer[] imageid1;
    private Activity context1;
    public AlphaAdapter(Activity context, String[] alphaNames, String[] objectNames1, Integer[] imageid1) {
        super(context, R.layout.alpha_list, alphaNames);
        this.context1 = context;
        this.alphaNames = alphaNames;
        this.objectNames1 = objectNames1;
        this.imageid1 = imageid1;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context1.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.alpha_list, null, true);
        TextView textViewCountry = row.findViewById(R.id.textViewAlpha);
        TextView textViewCapital =  row.findViewById(R.id.textViewAlpha2);
        ImageView imageFlag = row.findViewById(R.id.imageViewAlpha);

        textViewCountry.setText(alphaNames[position]);
        textViewCapital.setText(objectNames1[position]);
        imageFlag.setImageResource(imageid1[position]);
        return  row;
    }
}
