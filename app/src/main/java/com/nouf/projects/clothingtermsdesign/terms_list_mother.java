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
import java.util.HashMap;
import java.util.List;

public class terms_list_mother extends AppCompatActivity {


    ImageView mtoolbar;
    TextView toolbar_text_mother;
    ImageButton back_btn_mother;

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
        toolbar_text_mother = (TextView) findViewById(R.id.toolbar_text_mother);
        listViewTerms = (ListView) findViewById(R.id.list_mother);
        termlist = new ArrayList<>();
        array_list = new ArrayList<>();

        back_btn_mother = (ImageButton) findViewById(R.id.back_btn_mother);
        back_btn_mother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(terms_list_mother.this, terms_list.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        final String ref = getIntent().getExtras().getString("mRef", "defaultValue"); // till Term_id_101
        final String type = getIntent().getExtras().getString("type_id", "defaultValue"); // Types_of_id_101
        final boolean openActivity = getIntent().getBooleanExtra("open_machine_list_activity",false);

        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + ref + " ref in Mother 111111");
        Log.d(" ", "TTTTTTT!!!!g!!!!!!!!" + type + " type in Mother 11111");

        TermsDB = FirebaseDatabase.getInstance().getReference().child(ref);
        Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + TermsDB + " URL URL URL URL ");



        TermsDB.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //TermsDB = FirebaseDatabase.getInstance().getReference().child(ref).child(type);

                String title = dataSnapshot.child("arterm").getValue(String.class);
                Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + title + " title in Mother");
                Log.d(" ", "***************hhhhhhhhh" + dataSnapshot.getChildrenCount()+ "  children");

               toolbar_text_mother.setText(title);
                ArrayList<String> list = new ArrayList<>();
                List<String> key = new ArrayList<>();

                //termlist.clear();
              /*  DataSnapshot typeSnapshot = dataSnapshot.child(type);
                Log.d(" ", "!!!!!!!!!!!deeeeebbbbbuuuuuug!!!!!!!!!!!"+ "   DataSnapshot typeSnapshot = dataSnapshot.child(type) has executed successfully!!!! ");

                Iterable<DataSnapshot> typeChildren = typeSnapshot.getChildren();
                Log.d(" ", "!!!!!!!!!!!deeeeebbbbbuuuuuug!!!!!!!!!!!"+ "  Iterable<DataSnapshot> has executed successfully!!!! ");


                for(DataSnapshot typess : typeChildren){
                    Log.d(" ", "!!!!!!!!!!!deeeeebbbbbuuuuuug!!!!!!!!!!!"+ " for(DataSnapshot typess : typeChildren) has executed successfully!!!! ");*/

                Log.d(" ", "!!!!!!!!!!!deeeeebbbbbuuuuuug!!!!!!!!!!!" + type);



                for (DataSnapshot termsSnapshot : dataSnapshot.child(type).getChildren()) {

                   Log.d(" ", "!!!!!!!!!!!deeeeebbb inside second datasnap chot uuug!!!!!!!!!!!" +  termsSnapshot);

                   // HashMap<String, Object> map = (HashMap<String, Object>) typess.getValue();
                    //Log.d(" ", "!!!!!!!!!!!deeeeebbbbbuuuuuug!!!!!!!!!!!"+ termsSnapshot + "  children in Mother 2222222");
                    String term = termsSnapshot.child("arterm").getValue(String.class);
                    Log.d(" ", "!!!!!!!!!!!deeeeebbbbbuuuuuug!!!!!!!!!!!"+ term + " arabic term in children of the Mother"  );

                    list.add(term);
                    array_key = new ArrayList<>(list);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.listview_custom, list);
                listViewTerms.setAdapter(arrayAdapter);

                listViewTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("Debug", "this method has executed > setOnItemClickListener" );

                        int index = i;
                        position = Integer.toString(index);
                        completeRef = ref + "/" + type + "/" + position;
                        Intent intent;

                        if(openActivity) {
                            Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + "true!!!!!!!!!!");

                         //   intent = new Intent(terms_list_mother.this, machine_list.class);
                             intent = new Intent(terms_list_mother.this, details2.class);
                            intent.putExtra("type_id", type);

                        }else {
                            Log.d(" ", "gggggggggg!!!!g!!!!!!!!" + "false!!!!!!!!");

                            intent = new Intent(terms_list_mother.this, details2.class);
                            intent.putExtra("activit_checker", false);

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

                System.out.println("The read failed: " + databaseError.getCode());


            }
        });

    }
}
