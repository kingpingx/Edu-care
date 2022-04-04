package com.example.pythonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class canvasNumber extends AppCompatActivity {
    MyCanvas myCanvas;

    private View canvasView;
    private Button mbtnCheck;
    private TextView mresultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myCanvas= new MyCanvas(this,null);
        setContentView(R.layout.activity_canvas_number);
        canvasView=findViewById(R.id.myC);
        mbtnCheck=findViewById(R.id.btnCheck);
        mresultText= findViewById(R.id.resultText);
        if(!Python.isStarted())
        {
            Python.start(new AndroidPlatform(this));

        }
        mbtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int f=0;

                if(f==0) {
                    mresultText.setText("Try Again");
                    f++;
                }
                if(f!=0)
                {
                    mresultText.setText("Correct !");
                    f++;
                }
                String fileName = String.valueOf(Calendar.getInstance().getTimeInMillis());
// generate the image path
                String imagePath = Environment.getExternalStorageDirectory().toString() + File.separator +  fileName + ".jpg";

                try {

                    // save the image as png
                    FileOutputStream out = new FileOutputStream(imagePath);
                    // compress the image to png and pass it to the output stream
                    loadBitmapFromView(canvasView).compress(Bitmap.CompressFormat.JPEG, 90, out);

                    // save the image
                    out.flush();
                    out.close();
                    Python py= Python.getInstance();
                    PyObject pyt=py.getModule("hello");
                    PyObject obj=pyt.callAttr("utkarsh",imagePath);




                } catch (Exception error) {
                    Log.e("Error saving image", error.getMessage());
                }

            }
        });

    }

    public Bitmap loadBitmapFromView(View view) {

        // width measure spec
        int widthSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredWidth(), View.MeasureSpec.EXACTLY);
        // height measure spec
        int heightSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredHeight(), View.MeasureSpec.EXACTLY);
        // measure the view
        view.measure(widthSpec, heightSpec);

        // set the layout sizes
        view.layout(view.getLeft(), view.getTop(), view.getMeasuredWidth() + view.getLeft(), view.getMeasuredHeight() + view.getTop());
        // create the bitmap
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        // create a canvas used to get the view's image and draw it on the bitmap
        Canvas c = new Canvas(bitmap);
        // position the image inside the canvas
        c.translate(-view.getScrollX(), -view.getScrollY());
        // get the canvas
        view.draw(c);

        return bitmap;
    }
}