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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class terms_list_mother extends AppCompatActivity {


    ImageView mtoolbar;
    TextView toolbar_text;

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
        setContentView(R.layout.activity_terms_list_mother);

        mtoolbar = (ImageView) findViewById(R.id.mtoolbar);
        toolbar_text = (TextView) findViewById(R.id.toolbar_text_mother);
        listViewTerms = (ListView) findViewById(R.id.list_mother);
        termlist = new ArrayList<>();
        array_list = new ArrayList<>();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final String ref = getIntent().getExtras().getString("mRef", "defaultValue");
        final String type = getIntent().getExtras().getString("type_id", "defaultValue");
        final boolean openActivity = getIntent().getBooleanExtra("open_machine_list_activity",false);

        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + ref);
        Log.d(" ", "TTTTTTT!!!!g!!!!!!!!" + type);

        TermsDB = FirebaseDatabase.getInstance().getReference().child(ref);
        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + TermsDB);


        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String title = dataSnapshot.child("arterm").getValue(String.class);
                Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + title);
                toolbar_text.setText(title);
                ArrayList<String> list = new ArrayList<>();
                List<String> key = new ArrayList<>();
                termlist.clear();

                for (DataSnapshot termsSnapshot : dataSnapshot.child(type).getChildren()) {
                    Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + dataSnapshot.child(type).getChildren());
                    String term = termsSnapshot.child("arterm").getValue(String.class);
                    Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + term );
                    list.add(term);
                    array_key = new ArrayList<>(list);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.listview_custom, list);
                listViewTerms.setAdapter(arrayAdapter);

                listViewTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        int index = i;
                        position = Integer.toString(index);
                        completeRef = ref + "/" + type + "/" + position;
                        Intent intent;

                        if(openActivity) {
                            Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + "true!!!!!!!!!!");
                            intent = new Intent(terms_list_mother.this, machine_list.class);
                            intent.putExtra("type_id", type);

                        }else {
                            Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + "false!!!!!!!!");

                            intent = new Intent(terms_list_mother.this, details2.class);
                        }

                        intent.putExtra("mRef", completeRef);
                        //Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + dataSnapshot.child(prev + positionPlus1).child(type_id + positionPlus1).getChildrenCount());
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
