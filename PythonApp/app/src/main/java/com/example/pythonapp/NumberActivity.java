package com.example.pythonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class NumberActivity extends AppCompatActivity {
    private ListView listView;
    private String numberName[] = {"1","2","3","4","5","6","7","8","9"
    };


    private Integer imageid_number[] = {
            R.drawable.number_one,
            R.drawable.number_two,
            R.drawable.number_three,
            R.drawable.number_four,
            R.drawable.number_five,
            R.drawable.number_six,
            R.drawable.number_seven,
            R.drawable.number_eight,
            R.drawable.number_nine



    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        ListView listView=(ListView)findViewById(R.id.listN);

        // For populating list data
        NumberAdapter customCountryList = new NumberAdapter(this, numberName, imageid_number);
        listView.setAdapter(customCountryList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(NumberActivity.this, canvasNumber.class);
                startActivity(i);
            }
        });
    }
}