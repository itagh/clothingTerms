package com.nouf.projects.clothingtermsdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class machine_list extends AppCompatActivity {


    List<Terms> termlist;
    ArrayList<String> array_list;
    ArrayAdapter arrayAdapter;

    ArrayList<String> array_key;
    ListView listViewTerms;
    String Link = "";
    DatabaseReference TermsDB;

    String arabic_term;
    String english_term;
    Integer[] imgid;



    ImageView mtoolbar_machine;
    TextView toolbar_text_machine;
    ListView list_machine;


    ImageButton play_sign;
    ImageView machine_image;
    TextView machine_eng;
    TextView machine_arb;


    String imageUri = "hello";

    //  ArrayList<String> array_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_sewing_machines);


        mtoolbar_machine = (ImageView) findViewById(R.id.mtoolbar_machine);
        toolbar_text_machine = (TextView) findViewById(R.id.toolbar_text_machine);
        list_machine = (ListView) findViewById(R.id.list_machine);


        play_sign = (ImageButton) findViewById(R.id.play_sign);
        machine_image = (ImageView) findViewById(R.id.machine_image);
        machine_arb = (TextView) findViewById(R.id.machine_arb);
        machine_eng = (TextView) findViewById(R.id.machine_eng);


        termlist = new ArrayList<>();
        array_list = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final String ref = getIntent().getExtras().getString("mRef", "defaultValue");
        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + ref + "this is ref$$$$$$$$$$$$$$");

        final String type = getIntent().getExtras().getString("type_id", "defaultValue");
        TermsDB = FirebaseDatabase.getInstance().getReference().child(ref);


        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.child("arterm").getValue(String.class);  //انواع المكائن
                Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + title + "this is title");
                toolbar_text_machine.setText(title);


                ArrayList<String> list = new ArrayList<>();
                termlist.clear();
                for (DataSnapshot termsSnapshot : dataSnapshot.child(type).getChildren()) {//حيسمع لانواع المكائن


                    Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + dataSnapshot.child(type).getChildren() + "these are children");
                     arabic_term = termsSnapshot.child("arterm").getValue(String.class);
                     english_term= termsSnapshot.child("enterm").getValue(String.class);
                     imageUri = dataSnapshot.child("image").getValue(String.class);
                    Link = dataSnapshot.child("video").getValue(String.class);


                    Log.d("Print", "the arabic term" + arabic_term );
                    Log.d("Print", "the english term" + english_term );
                    Log.d("Print", "the img uri" + imageUri );
                    Log.d("Print", "the video link" + Link );



                    Picasso.get().load(imageUri).into(machine_image);

                    machine_arb.setText(arabic_term);
                    machine_eng.setText(english_term);

                    play_sign.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("Link inside Button", "" + Link);
                            Intent intent = new Intent(machine_list.this, videoPlayer.class);
                            intent.putExtra("URL", Link);
                            startActivity(intent);
                        }
                    });
                    array_key = new ArrayList<>(list);

                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.machine_list, list);
                listViewTerms.setAdapter(arrayAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
