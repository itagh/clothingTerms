package com.nouf.projects.clothingtermsdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {

    ImageButton imageButton_category_1;
    ImageButton imageButton_category_2;
    ImageButton imageButton_category_3;
    ImageButton imageButton_category_4;
    ImageButton imageButton_category_5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        imageButton_category_1 = (ImageButton) findViewById(R.id.imageButton_category_1_2);
        imageButton_category_2 = (ImageButton) findViewById(R.id.imageButton_category_2_2);
        imageButton_category_3 = (ImageButton) findViewById(R.id.imageButton_category_3_2);
        imageButton_category_4 = (ImageButton) findViewById(R.id.imageButton_category_4_2);
        imageButton_category_5 = (ImageButton) findViewById(R.id.imageButton_category_5_2);


        // One image button clicked: (Make all actions)
        imageButton_category_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(Main2Activity.this, terms_list.class);
                intent.putExtra("category", 1); //sent id of category
                startActivity(intent);
            }
        });
        imageButton_category_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(Main2Activity.this, terms_list.class);
                intent.putExtra("category", 2);
                startActivity(intent);
            }
        });
        imageButton_category_3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(Main2Activity.this, terms_list.class);
                intent.putExtra("category", 3);
                startActivity(intent);
            }
        });
        imageButton_category_4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
               Intent intent = new Intent(Main2Activity.this, terms_list.class);
                intent.putExtra("category", 4);
                startActivity(intent);
            }
        });
        imageButton_category_5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent intent = new Intent(Main2Activity.this, terms_list.class);
                intent.putExtra("category", 5);
                startActivity(intent);
            }
        });


    }


}




