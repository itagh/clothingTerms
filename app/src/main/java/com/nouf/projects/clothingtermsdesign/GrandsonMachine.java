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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GrandsonMachine extends AppCompatActivity {
    ImageView term_image, imageView_logo_details;
    ImageButton imageButton_play;
    TextView textView_arb_term_2, textView_arb_def_2, textView_eng_term_2, textView_eng_def_2;
    ListView Termlistview3;
    String Link = "";
    DatabaseReference TermsDB;
    String completeRef, completeType, grandsuntypes;
    List<Terms> termlist;
    ArrayList<String> array_list;
    ArrayAdapter arrayAdapter;
    ArrayList<String> array_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grandson_machine);
        imageButton_play = (ImageButton) findViewById(R.id.imageButton_play);
        imageView_logo_details = (ImageView) findViewById(R.id.imageView_logo_details);
        term_image = (ImageView) findViewById(R.id.term_image);
        textView_arb_term_2 = (TextView) findViewById(R.id.textView_arb_term_2);
        textView_arb_def_2 = (TextView) findViewById(R.id.textView_arb_def_2);
        textView_eng_term_2 = (TextView) findViewById(R.id.textView_eng_term_2);
        textView_eng_def_2 = (TextView) findViewById(R.id.textView_eng_def_2);
        Termlistview3 = (ListView) findViewById(R.id.Termlistview4);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        completeRef = getIntent().getExtras().getString("mRef", "defaultValue"); //catname / Term_id_403
        completeType = getIntent().getExtras().getString("type_id", "defaultValue"); //Types_of_403/Types_of_403_2 from machine (2)

        grandsuntypes = getIntent().getExtras().getString("grand_id", "defaultValue");
        Log.d(" ", "COMPLETE REF             " + completeRef + "       and COMPLETE TYPE " + completeType +"    grandsonType      "+grandsuntypes);
        TermsDB = FirebaseDatabase.getInstance().getReference().child(completeRef + "/" + completeType);
        Log.d(" ", "Term Database is " + TermsDB);
        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                Log.d(" ", "TOTAL CHILDREN" + dataSnapshot.getChildrenCount() + "these are children");
                String title = dataSnapshot.child("arterm").getValue(String.class);
                Log.d(" ", "TOOL BAR TITLE" + title + "this is title");
                // toolbar_text_machine.setText(title);
                // (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                String arabic_term = dataSnapshot.child("arterm").getValue(String.class);
                String arabic_definition = dataSnapshot.child("ardef").getValue(String.class);
                String english_term = dataSnapshot.child("enterm").getValue(String.class);
                String english_definition = dataSnapshot.child("endef").getValue(String.class);
                Log.d("ar term en term", "" + arabic_term + "     " + arabic_definition + "    " + english_term + "   " + english_definition);
                // String imageUri = dataSnapshot.child("image").getValue(String.class);
                //Link = dataSnapshot.child("video").getValue(String.class);
                //Picasso.get().load(imageUri).into(term_image);
                textView_arb_term_2.setText(arabic_term);
                textView_arb_def_2.setText(arabic_definition);
                textView_eng_term_2.setText(english_term);
                textView_eng_def_2.setText(english_definition);
                imageButton_play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d("Link inside Button", "" + Link);
                        Intent intent = new Intent(GrandsonMachine.this, videoPlayer.class);
                        intent.putExtra("URL", Link);
                        startActivity(intent);
                    }
                });
                Log.d(" ", "Data snapchot print" + TermsDB + "these are children");

                ArrayList<String> list = new ArrayList<>();
                //List<String> key = new ArrayList<>();
                // termlist.clear();
                for(DataSnapshot grandlist : dataSnapshot.getChildren()){
                    String arterm = grandlist.child("arterm").getValue(String.class);
                    list.add(arterm);
                    array_key = new ArrayList<>(list);

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.listview_custom, list);

                Termlistview3.setAdapter(arrayAdapter);
                Log.d(" ", "listview3" + Termlistview3.getChildAt(0));
                Termlistview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int positionplus1 = position + 1;
                        String index = Integer.toString(positionplus1);
                        String grandSonTypes = completeType + "_" + index;
                        completeType = grandSonTypes;
                        Log.d(" ", "Data snapchot print" + completeType + "thesejkfdmfdmfdmfn are children"); // types_of_id_403/Types_of_id_403_2_1
                        Intent intent = new Intent(GrandsonMachine.this, details2.class);
                        intent.putExtra("mRef", completeRef);
                        intent.putExtra("type_id", completeType);
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
