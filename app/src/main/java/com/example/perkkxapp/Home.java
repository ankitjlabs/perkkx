package com.example.perkkxapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by wadi_123 on 5/18/2015.
 */
public class Home extends Activity {

    ImageButton next ,dinning;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single);

        dinning=(ImageButton)findViewById(R.id.dinbt);



        next=(ImageButton)findViewById(R.id.imageButton2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(Home.this,HomeSecond.class);
                startActivity(intent);
            }
        });


        dinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(Home.this,MainUtilpage.class);
                startActivity(intent);
            }
        });



}}
