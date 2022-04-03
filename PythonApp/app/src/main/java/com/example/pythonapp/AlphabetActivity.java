package com.example.pythonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlphabetActivity extends AppCompatActivity {
private  ListView listView;
    private String alphaName[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q",
            "R","S","T","U","V","W","X","Y","Z"

    };

    private String ObjectNames[] = {
            "Apple", "Ball", "Cat", "Dog","Egg","Fish","Grapes","Hat","Ice-cream","Jug","Kite","Lion","Mango"
            ,"Nest","Orange","Parrot","Queen","Rat","Ship","Tiger","Umbrella","Van","watch","X-mas Tree","Yacht"
            ,"Zebra"
    };
    private Integer imageid_alpha[] = {
            R.drawable.apple,
            R.drawable.ball,
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.egg,
            R.drawable.fish,
            R.drawable.grapes,
            R.drawable.hat,
            R.drawable.icecream,
            R.drawable.jug,
            R.drawable.kite,
            R.drawable.lion,
            R.drawable.mango,
            R.drawable.nest,
            R.drawable.orange,
            R.drawable.parrot,
            R.drawable.queen,
            R.drawable.rat,
            R.drawable.ship,
            R.drawable.tiger,
            R.drawable.umbrella,
            R.drawable.van,
            R.drawable.watch,
            R.drawable.yacht, R.drawable.xmastree,
            R.drawable.zebra


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);


        ListView listView=(ListView)findViewById(R.id.listV);

        // For populating list data
        AlphaAdapter customCountryList = new AlphaAdapter(this, alphaName, ObjectNames, imageid_alpha);
        listView.setAdapter(customCountryList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(AlphabetActivity.this, canvasActivity.class);
                startActivity(i);
                }
        });

    }
}