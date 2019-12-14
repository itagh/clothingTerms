package com.nouf.projects.clothingtermsdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class details extends AppCompatActivity {

    TextView textView_arb_term, textView_arb_def, textView_eng_term, textView_eng_def;
    List<Terms> termsList;
    ArrayList<String> array_list;
    DatabaseReference TermsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView_arb_term = (TextView) findViewById(R.id.textView_arb_term);
        textView_arb_def = (TextView) findViewById(R.id.textView_arb_def);
        textView_eng_term = (TextView) findViewById(R.id.textView_eng_term);
        textView_eng_def = (TextView) findViewById(R.id.textView_eng_def);

        //String completeRef =
        //String [] ss = completeRef.split("/");
        termsList = new ArrayList<>();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String ref = getIntent().getExtras().getString("db", "defaultValue");
        TermsDB = FirebaseDatabase.getInstance().getReference().child(ref);
        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + TermsDB);


        // completeRef = intent.getIntExtra.("", completeRef);
        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(), String.valueOf(dataSnapshot.getChildrenCount()), Toast.LENGTH_LONG).show();
                String counter = String.valueOf(dataSnapshot.getChildrenCount());

                    //ArrayList<String> list = new ArrayList<>();
                   // List<String> key1 = new ArrayList<>();
                     termsList.clear();
                    // (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                       String at = dataSnapshot.child("arterm").getValue(String.class);
                        String ad = dataSnapshot.child("ardef").getValue(String.class);
                        String et = dataSnapshot.child("enterm").getValue(String.class);
                        String ed = dataSnapshot.child("endef").getValue(String.class);
                        // Log.d("test", "" + at +" "+ad + " "+ et+" "+ed);
                        Log.d("",at + " " + ad + " " + et + " " + ed);
                        //Log.d("test", "" + list);
                       // array_list = new ArrayList<>(list);
                        textView_arb_term.setText(at);
                        textView_arb_def.setText(ad);
                        textView_eng_term.setText(et);
                        textView_eng_def.setText(ed);
                   // }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}