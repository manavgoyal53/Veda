package com.example.veda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    Button settings;
    Button ecg;
    Button heart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        settings = findViewById(R.id.button4);
        ecg = findViewById(R.id.button5);
        heart = findViewById(R.id.button6);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(Main2Activity.this , HomeActivity.class);
                startActivity(i);

            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Main2Activity.this, HeartActivity.class);
//                startActivity(i);
            }
        });
        ecg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent i = new Intent(Main2Activity.this,EcgActivity.class);
            }
        });
    }
}
