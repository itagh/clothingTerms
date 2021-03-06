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

public class machine_list extends AppCompatActivity {

    ImageView term_image, imageView_logo_details;
    ImageButton imageButton_play;
    TextView textView_arb_term_1, textView_arb_def_1, textView_eng_term_1, textView_eng_def_1;
    ListView listViewTerms2;
    String Link = "";
    DatabaseReference  TermsDB;
    String completeRef, completeType;

    List<Terms> termlist;
    ArrayList<String> array_list;
    ArrayAdapter arrayAdapter;
    ArrayList<String> array_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_list);
        imageButton_play = (ImageButton) findViewById(R.id.imageButton_play);
        imageView_logo_details = (ImageView) findViewById(R.id.imageView_logo_details);
        term_image = (ImageView) findViewById(R.id.term_image);
        textView_arb_term_1 = (TextView) findViewById(R.id.textView_arb_term_2);
        textView_arb_def_1 = (TextView) findViewById(R.id.textView_arb_def_2);
        textView_eng_term_1 = (TextView) findViewById(R.id.textView_eng_term_2);
        textView_eng_def_1 = (TextView) findViewById(R.id.textView_eng_def_2);
        listViewTerms2 = (ListView) findViewById(R.id.listViewTerms3);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        completeRef = getIntent().getExtras().getString("mRef", "defaultValue");
        completeType =getIntent().getExtras().getString("type_id", "defaultValue");
        Log.d(" ", "complete refrence         " + completeRef + "           and COMPLETE TYPE       "+ completeType);

        TermsDB = FirebaseDatabase.getInstance().getReference().child(completeRef); // catName/Term_id_403


        TermsDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                //Log.d(" ", "TOTAL CHILDREN" + dataSnapshot.getChildrenCount() + "these are children");
                String title = dataSnapshot.child("arterm").getValue(String.class);
               // Log.d(" ", "TOOL BAR TITLE" + title + "this is title");
               // toolbar_text_machine.setText(title);
                // (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                String arabic_term = dataSnapshot.child("arterm").getValue(String.class);
                String arabic_definition = dataSnapshot.child("ardef").getValue(String.class);
                String english_term = dataSnapshot.child("enterm").getValue(String.class);
                String english_definition = dataSnapshot.child("endef").getValue(String.class);
               // String imageUri = dataSnapshot.child("image").getValue(String.class);
                //Link = dataSnapshot.child("video").getValue(String.class);

                //Picasso.get().load(imageUri).into(term_image);

                textView_arb_term_1.setText(arabic_term);
                textView_arb_def_1.setText(arabic_definition);
                textView_eng_term_1.setText(english_term);
                textView_eng_def_1.setText(english_definition);

                imageButton_play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Link inside Button", "" + Link);
                        Intent intent = new Intent(machine_list.this, videoPlayer.class);
                        intent.putExtra("URL", Link);
                        startActivity(intent);
                    }
                });


                ArrayList<String> list = new ArrayList<>();
                List<String> key = new ArrayList<>();
               // termlist.clear();
                for (DataSnapshot termsSnapshot : dataSnapshot.child(completeType).getChildren()) {


                    //Log.d(" ", "Data snapchot print" + dataSnapshot.child(completeType)+"these are children");
                    String arterm = termsSnapshot.child("arterm").getValue(String.class);



                    //String term2 = termsSnapshot.child("Term_id_101").child("arterm").getValue(String.class);
                    list.add(arterm);
                    array_key = new ArrayList<>(list);
                }//for
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.listview_custom, list);
                listViewTerms2.setAdapter(arrayAdapter);

                listViewTerms2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int positionplus1 = position+1;
                        String index = Integer.toString(positionplus1);
                        String grandSonTypes = completeType+"_"+index;

                        completeType = completeType+"/"+grandSonTypes; ///Term_id_403/Types_of_id_403
                       // Log.d(" ", "111111111111111111" + completeType+"11111111111111111");

                        //Log.d(" ", "Data snapchot print" + completeType+"thesejkfdmfdmfdmfn are children");
                       // Log.d(" ", "Data snapchot print" +dataSnapshot.getChildren()+" count children");

                        Intent intent = new Intent(machine_list.this, GrandsonMachine.class);
                        intent.putExtra("mRef", completeRef);
                        Log.d(" ", "Data snapchot print" +dataSnapshot.getChildren()+" couvvvvvvvnt children");
                        intent.putExtra("type_id",completeType);
                        intent.putExtra("grand_id",grandSonTypes);

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
