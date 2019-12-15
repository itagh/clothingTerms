package com.nouf.projects.clothingtermsdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MotherList extends AppCompatActivity {

    Toolbar toolbar;
    List<Terms> termlist;
    ArrayList<String> array_list;
    ArrayAdapter arrayAdapter;

    ArrayList<String> array_key;
    ListView listViewTerms;
    DatabaseReference TermsDB;
    String position = "";
    String completeRef = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_list);


        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle(""); // Must to set Title null first.
        setSupportActionBar(toolbar); // Make toolbar act as ActionBar


        listViewTerms = (ListView) findViewById(R.id.listViewTerms2);
        termlist = new ArrayList<>();
        array_list = new ArrayList<>();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final String ref = getIntent().getExtras().getString("db", "defaultValue");
        final String T = getIntent().getExtras().getString("Types", "defaultValue");
        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + ref);
        Log.d(" ", "TTTTTTT!!!!g!!!!!!!!" + T);
        TermsDB = FirebaseDatabase.getInstance().getReference().child(ref);
        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + TermsDB);


        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.child("arterm").getValue(String.class);
                Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + title);
                toolbar.setTitle(title);
                ArrayList<String> list = new ArrayList<>();
                List<String> key = new ArrayList<>();
                termlist.clear();

                for (DataSnapshot termsSnapshot : dataSnapshot.child(T).getChildren()) {
                    Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + dataSnapshot.child(T).getChildren());
                    String term = termsSnapshot.child("arterm").getValue(String.class);
                    //String term2 = termsSnapshot.child("Term_id_101").child("arterm").getValue(String.class);
                    list.add(term);
                    array_key = new ArrayList<>(list);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.listview_custom, list);
                listViewTerms.setAdapter(arrayAdapter);

                listViewTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        int index = i;
                        position =  Integer.toString(index);
                        completeRef = ref+"/"+T+"/"+position;

                        Intent intent = new Intent(MotherList.this, details.class);
                        intent.putExtra("db", completeRef);
                       // Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + dataSnapshot.child(prev + idd).child(Types + idd).getChildrenCount());
                        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + completeRef);
                        startActivity(intent);
                    }





                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
