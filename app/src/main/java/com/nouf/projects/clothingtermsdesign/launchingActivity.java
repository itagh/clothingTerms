package com.nouf.projects.clothingtermsdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class launchingActivity extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 2000;
    ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);

        imageViewLogo = (ImageView) findViewById(R.id.imageViewLogo);
        setContentView(R.layout.activity_launching);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent (launchingActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
