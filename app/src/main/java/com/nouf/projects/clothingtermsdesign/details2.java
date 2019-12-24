package com.nouf.projects.clothingtermsdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class details2 extends AppCompatActivity {

    TextView textView_arb_term, textView_arb_def, textView_eng_term, textView_eng_def;
    DatabaseReference TermsDB;
    String Link = "";
    ImageView term_image;
    ImageButton play_btn;
    ImageButton back_btn_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

       final Boolean activit_checker = getIntent().getExtras().getBoolean("activit_checker");



        textView_arb_term = (TextView) findViewById(R.id.textView_arb_term_2);
        textView_arb_def = (TextView) findViewById(R.id.textView_arb_def_2);
        textView_eng_term = (TextView) findViewById(R.id.textView_eng_term_2);
        textView_eng_def = (TextView) findViewById(R.id.textView_eng_def_2);
        term_image = (ImageView) findViewById(R.id.term_image);
        play_btn = (ImageButton) findViewById(R.id.imageButton_play);
        back_btn_details = (ImageButton) findViewById(R.id.back_btn_details);


        back_btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (activit_checker) {
                    intent = new Intent(details2.this, terms_list.class);
                } else {
                    intent = new Intent(details2.this, terms_list_mother.class);
                }
                startActivity(intent);

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String ref = getIntent().getExtras().getString("mRef", "defaultValue");
        String type_id = getIntent().getExtras().getString("type_id", "defaultValue");

        if(!type_id.isEmpty()){
            TermsDB = FirebaseDatabase.getInstance().getReference().child(ref);
            //   Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + TermsDB);


            TermsDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //Toast.makeText(getApplicationContext(),
                    // String.valueOf(dataSnapshot.getChildrenCount()), Toast.LENGTH_LONG).show();


                    // (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                    String arabic_term = dataSnapshot.child("arterm").getValue(String.class);
                    String arabic_definition = dataSnapshot.child("ardef").getValue(String.class);
                    String english_term = dataSnapshot.child("enterm").getValue(String.class);
                    String english_definition = dataSnapshot.child("endef").getValue(String.class);
                    //String imageUri = dataSnapshot.child("image").getValue(String.class);
                    // Link = dataSnapshot.child("video").getValue(String.class);


                    // Picasso.get().load(imageUri).into(term_image);

                    textView_arb_term.setText(arabic_term);
                    textView_arb_def.setText(arabic_definition);
                    textView_eng_term.setText(english_term);
                    textView_eng_def.setText(english_definition);

               /* play_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Link inside Button", "" + Link);
                        Intent intent = new Intent(details2.this, videoPlayer.class);
                        intent.putExtra("URL", Link);
                        startActivity(intent);
                    }
                });*/

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else {
            TermsDB = FirebaseDatabase.getInstance().getReference().child(ref+"/"+type_id);
             Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + TermsDB);


            TermsDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //Toast.makeText(getApplicationContext(),
                    // String.valueOf(dataSnapshot.getChildrenCount()), Toast.LENGTH_LONG).show();


                    // (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                    String arabic_term = dataSnapshot.child("arterm").getValue(String.class);
                    String arabic_definition = dataSnapshot.child("ardef").getValue(String.class);
                    String english_term = dataSnapshot.child("enterm").getValue(String.class);
                    String english_definition = dataSnapshot.child("endef").getValue(String.class);
                    //String imageUri = dataSnapshot.child("image").getValue(String.class);
                    // Link = dataSnapshot.child("video").getValue(String.class);


                    // Picasso.get().load(imageUri).into(term_image);

                    textView_arb_term.setText(arabic_term);
                    textView_arb_def.setText(arabic_definition);
                    textView_eng_term.setText(english_term);
                    textView_eng_def.setText(english_definition);

               /* play_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Link inside Button", "" + Link);
                        Intent intent = new Intent(details2.this, videoPlayer.class);
                        intent.putExtra("URL", Link);
                        startActivity(intent);
                    }
                });*/

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }




    }
}