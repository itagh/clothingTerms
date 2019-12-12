package com.nouf.projects.clothingtermsdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class inside_category extends AppCompatActivity {
    Toolbar toolbar;
    List<Terms> termlist;
    ArrayList<String> array_list;
    ArrayAdapter arrayAdapter;

    ArrayList<String> array_key;
    ListView listViewTerms;
    DatabaseReference TermsDB ;
    String Ref = "";
    String prev = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_category);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(""); // Must to set Title null first.
        setSupportActionBar(toolbar); // Make toolbar act as ActionBar


        listViewTerms = (ListView) findViewById(R.id.listViewTerms);
        termlist = new ArrayList<>();
        array_list = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final int category_number = intent.getIntExtra("intVariableName", 0);
        // Set the title on toolbar based on category:
        if(category_number ==1) {
            toolbar.setTitle("المصطلحات الخاصة بالرسم والتصميم");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Drawing");
            Ref = "Terms_of_Drawing";
            prev = "Term_id_20";
        }
        if(category_number ==2) {
            toolbar.setTitle("مصطلحات أدوات التفصيل والحياكة");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Knitting_and_Tailoring");
            Ref = "Terms_of_Knitting_and_Tailoring";
            prev = "Term_id_10";
        }
        if(category_number ==3) {
            toolbar.setTitle("مصطلحات أقمشة الملابس");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Clothing_Fabrics");
            prev = "Term_id_30";
        }
        if(category_number ==4) {
            toolbar.setTitle("مصطلحات ماكينة الخياطة");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Sewing_Machine");
            prev = "Term_id_40";
        }
        if(category_number ==5) {
            toolbar.setTitle("المصطلحات العامة للملابس");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_Generals");
            prev = "Term_id_50";
        }



        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> list = new ArrayList<>();


                List<String> key = new ArrayList<>();
                termlist.clear();

                for(DataSnapshot termsSnapshot : dataSnapshot.getChildren()){
                    String term = termsSnapshot.child("arterm").getValue(String.class);
                    list.add(term);
                    array_key = new ArrayList<>(list);
                }




                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.font_color, list);
                listViewTerms.setAdapter(arrayAdapter);

                listViewTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        int index = position+1;
                        String idd = Integer.toString(index);
                        String completeRef = Ref+"/"+prev+idd;
                        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + idd);
                        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + completeRef);
                       // TermsDB = FirebaseDatabase.getInstance().getReference().child(ss[0]).child(ss[1]);
                        Intent intent = new Intent(inside_category.this, details.class);
                        char [] a = completeRef.toCharArray();
                        intent.putExtra("db", completeRef);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }*/


}
