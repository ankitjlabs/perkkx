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
public class HomeSecond extends Activity {

    ImageButton back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        back=(ImageButton)findViewById(R.id.imageButton2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(HomeSecond.this,Home.class);
                startActivity(i);
                finish();
            }
        });


    }
}
