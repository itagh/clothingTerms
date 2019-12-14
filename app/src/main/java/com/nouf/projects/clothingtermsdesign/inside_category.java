package com.nouf.projects.clothingtermsdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
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
    DatabaseReference TermsDB;
    String Ref = "";
    String prev = "";
    String prev2 = "";
    String Types = "";
    String Types2 = "";
    String idd = "";
    String completeRef = "";

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
        if (category_number == 1) {
            toolbar.setTitle("المصطلحات الخاصة بالرسم والتصميم");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_for_Drawing_and_Design");
            Ref = "Terms_for_Drawing_and_Design";
            prev = "Term_id_20";
            prev2 = "Term_id_2";
            Types = "Types_of_id_20";
            Types2= "Types_of_id_2";
        }
        if (category_number == 2) {
            toolbar.setTitle("مصطلحات أدوات التفصيل والحياكة");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Knitting_and_Tailoring");
            Ref = "Terms_of_Knitting_and_Tailoring";
            prev = "Term_id_10";
            prev2 = "Term_id_1";
            Types = "Types_of_id_10";
            Types2= "Types_of_id_1";
        }
        if (category_number == 3) {
            toolbar.setTitle("مصطلحات أقمشة الملابس");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Clothing_Fabrics");
            Ref = "Terms_of_Clothing_Fabrics";
            prev = "Term_id_30";
            prev2 = "Term_id_3";
            Types = "Types_of_id_30";
            Types2= "Types_of_id_3";

        }
        if (category_number == 4) {
            toolbar.setTitle("مصطلحات ماكينة الخياطة");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_of_Sewing_Machine");
            Ref = "Terms_of_Sewing_Machine";
            prev = "Term_id_40";
            prev2 = "Term_id_4";
            Types = "Types_of_id_40";
            Types2= "Types_of_id_4";
        }
        if (category_number == 5) {
            toolbar.setTitle("المصطلحات العامة للملابس");
            TermsDB = FirebaseDatabase.getInstance().getReference().child("Terms_Generals");
            Ref = "Terms_Generals";
            prev = "Term_id_50";
            prev2 = "Term_id_5";
            Types = "Types_of_id_50";
            Types2= "Types_of_id_5";
        }
        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + TermsDB);
        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                ArrayList<String> list = new ArrayList<>();
                List<String> key = new ArrayList<>();
                termlist.clear();
                for (DataSnapshot termsSnapshot : dataSnapshot.getChildren()) {
                    String term = termsSnapshot.child("arterm").getValue(String.class);
                    //String term2 = termsSnapshot.child("Term_id_101").child("arterm").getValue(String.class);
                    list.add(term);
                    array_key = new ArrayList<>(list);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.font_color, list);
                listViewTerms.setAdapter(arrayAdapter);
                listViewTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        int index = position + 1;
                        int num = index;
                        int count = 0;
                        while (num != 0) {
                            // num = num/10
                            num /= 10;
                            ++count;
                        }
                        long counterPrerv = dataSnapshot.child(prev + idd).getChildrenCount();
                        long counterPrev2 = dataSnapshot.child(prev2 + idd).getChildrenCount();
                        if (count == 1) {
                            idd = Integer.toString(index);
                            completeRef = Ref + "/" + prev + idd;
                            Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + "count == 1");


                            if (counterPrerv == 5) { // no children
                                Intent intent = new Intent(inside_category.this, details.class);
                                intent.putExtra("db", completeRef);
                                startActivity(intent);

                            } else if (counterPrerv == 2) {
                                Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + dataSnapshot.child(prev + idd).child(Types + idd).getChildrenCount());
                                String T = Types+idd;
                                Intent intent = new Intent(inside_category.this, MotherList.class);
                                intent.putExtra("db", completeRef);
                                intent.putExtra("Types",T);
                                startActivity(intent);
                            }

                        } else {
                            idd = Integer.toString(index);
                            completeRef = Ref + "/" + prev2 + idd;
                            String T = Types2+idd;
                            //Log.d(" ", "gggggggggg!!!!g!!!!!!!!" +  completeRef);
                            if (counterPrev2 == 5) { // no children
                                Intent intent = new Intent(inside_category.this, details.class);
                                intent.putExtra("db", completeRef);
                                startActivity(intent);

                            } else if (counterPrev2 == 2) {
                                Intent intent = new Intent(inside_category.this, MotherList.class);
                                intent.putExtra("db", completeRef);
                                intent.putExtra("Types",T);
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

    /*public void toastmsg(CharSequence text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }*/
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
