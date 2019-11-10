package com.example.veda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    ImageView settings;
    ImageView ecg;
    ImageView analytics;
    ImageView activity;
    ImageView heart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ecg = findViewById(R.id.imageView2);
        heart = findViewById(R.id.imageView4);
        analytics = findViewById(R.id.imageView5);
        activity = findViewById(R.id.imageView6);
        settings = findViewById(R.id.imageView7);
        settings.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent i  = new Intent(Main2Activity.this , HomeActivity.class);
                startActivity(i);
           }
        });
        ecg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i  = new Intent(Main2Activity.this , EcgActivity.class);
               startActivity(i);
           }
        });
        heart.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
               Intent i  = new Intent(Main2Activity.this , HeartActivity.class);
              startActivity(i);
           }
        });
        analytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,AnalyticsActivity.class));
            }
        });
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,ActivityActivity.class));
            }
        });

    }
}
