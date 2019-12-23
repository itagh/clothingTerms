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

public class terms_list extends AppCompatActivity {

    ImageView mtoolbar;
    TextView toolbar_text;
    ImageButton back_btn;


    List<Terms> termlist;
    ArrayList<String> array_list;
    String the_type = "";
    String completeType = "";
    ArrayList<String> array_key;
    ListView listViewTerms;
    DatabaseReference TermsDB;
    String category_name = "";
    String term_id = "";
    String term_id_2digits = "";
    String type_id = "";
    String type_id_2digits = "";
    String positionPlus1 = "";
    String completeRef = "";
    boolean openActivity = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);
       /* toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(""); // Must to set Title null first.
        setSupportActionBar(toolbar); // Make toolbar act as ActionBar*/


        mtoolbar = (ImageView) findViewById(R.id.mtoolbar);
        toolbar_text = (TextView) findViewById(R.id.toolbar_text);


        listViewTerms = (ListView) findViewById(R.id.list);
        termlist = new ArrayList<>();
        array_list = new ArrayList<>();


       back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(terms_list.this, Main2Activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final int category_number = intent.getIntExtra("category", 0);
        // Set the title on toolbar based on category:
        if (category_number == 1) {
            toolbar_text.setText("المصطلحات الخاصة بالرسم والتصميم");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_for_Drawing_and_Design");
            category_name = "Terms_for_Drawing_and_Design";
            term_id = "Term_id_20";
            term_id_2digits = "Term_id_2";
            type_id = "Types_of_id_20";
            type_id_2digits = "Types_of_id_2";
        }
        if (category_number == 2) {
            toolbar_text.setText("مصطلحات أدوات التفصيل والحياكة");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Knitting_and_Tailoring");
            category_name = "Terms_of_Knitting_and_Tailoring";
            term_id = "Term_id_10";
            term_id_2digits = "Term_id_1";
            type_id = "Types_of_id_10";
            type_id_2digits = "Types_of_id_1";
        }
        if (category_number == 3) {
            toolbar_text.setText("مصطلحات أقمشة الملابس");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Clothing_Fabrics");
            category_name = "Terms_of_Clothing_Fabrics";
            term_id = "Term_id_30";
            term_id_2digits = "Term_id_3";
            type_id = "Types_of_id_30";
            type_id_2digits = "Types_of_id_3";

        }
        if (category_number == 4) {
            toolbar_text.setText("مصطلحات ماكينة الخياطة");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Sewing_Machine");
            category_name = "Terms_of_Sewing_Machine";
            term_id = "Term_id_40";
            type_id = "Types_of_id_40";
            openActivity = true;

        }
        if (category_number == 5) {
            toolbar_text.setText("المصطلحات العامة للملابس");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_Generals");
            category_name = "Terms_Generals";
            term_id = "Term_id_50";
            term_id_2digits = "Term_id_5";
            type_id = "Types_of_id_50";
            type_id_2digits = "Types_of_id_5";
        }
        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + TermsDB + " URL URL URL URL URL");

        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                ArrayList<String> list = new ArrayList<>();
                termlist.clear(); //????????????


                for (DataSnapshot termsSnapshot : dataSnapshot.getChildren()) {
                    String term = termsSnapshot.child("arterm").getValue(String.class);
                    list.add(term);
                    array_key = new ArrayList<>(list);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.listview_custom, list);
                listViewTerms.setAdapter(arrayAdapter);

                listViewTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        int index = position + 1;
                        int num = index;
                        int count = 0;
                        while (num != 0) {
                            num /= 10;
                            ++count;
                        }
                        positionPlus1 = Integer.toString(index);


                        long child_counter = dataSnapshot.child(term_id + positionPlus1).getChildrenCount();
                        long child_counter_2digit = dataSnapshot.child(term_id_2digits + positionPlus1).getChildrenCount();

                        if (count == 1) { //  counter with 1 digit:

                            completeRef = category_name + "/" + term_id + positionPlus1;

                            if (child_counter == 5) { // Single:
                                Intent intent = new Intent(terms_list.this, details2.class);
                                intent.putExtra("mRef", completeRef);
                                intent.putExtra("activit_checker", true);
                                startActivity(intent);

                            } else if (child_counter == 2) { // Mother:
                                completeType = type_id + positionPlus1; // types_of_id_10+0

                                Intent intent = new Intent(terms_list.this, terms_list_mother.class);
                                intent.putExtra("mRef", completeRef);
                                intent.putExtra("type_id", completeType);
                                intent.putExtra("open_machine_list_activity", openActivity);
                                startActivity(intent);
                            }

                        } else { //  counter with 2 or more digits:

                            completeRef = category_name + "/" + term_id_2digits + positionPlus1;
                            the_type = type_id_2digits + positionPlus1;

                            //Log.d(" ", "gggggggggg!!!!g!!!!!!!!" +  completeRef);
                            if (child_counter_2digit == 5 ) { // Single
                                Intent intent = new Intent(terms_list.this, details2.class);
                                intent.putExtra("mRef", completeRef);
                                intent.putExtra("activit_checker", true);
                                startActivity(intent);

                            } else if (child_counter_2digit == 2) { // Mother
                                Intent intent = new Intent(terms_list.this, terms_list_mother.class);
                                intent.putExtra("mRef", completeRef);
                                intent.putExtra("type_id", the_type);
                                startActivity(intent);
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

