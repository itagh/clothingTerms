package com.nouf.projects.clothingtermsdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class details extends AppCompatActivity {

    TextView textView_arb_term, textView_arb_def, textView_eng_term, textView_eng_def;
  //  List< Terms> termsList;
   // ArrayList<String> array_list;
    DatabaseReference TermsDB;
    Button btn_vid;
    Uri uri;
    String Link = "";
    ImageView imageViewTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView_arb_term = (TextView) findViewById(R.id.textView_arb_term);
        textView_arb_def = (TextView) findViewById(R.id.textView_arb_def);
        textView_eng_term = (TextView) findViewById(R.id.textView_eng_term);
        textView_eng_def = (TextView) findViewById(R.id.textView_eng_def);
        imageViewTerm = (ImageView) findViewById(R.id.imageViewTerm);

        btn_vid = (Button) findViewById(R.id.vid_btn);



       // termsList = new ArrayList<>();
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
                Toast.makeText(getApplicationContext(),
                        String.valueOf(dataSnapshot.getChildrenCount()), Toast.LENGTH_LONG).show();
                String counter = String.valueOf(dataSnapshot.getChildrenCount());

                    //ArrayList<String> list = new ArrayList<>();
                   // List<String> key1 = new ArrayList<>();
                //     termsList.clear();
                    // (DataSnapshot detailsSnapshot : dataSnapshot.getChildren()) {
                       String at = dataSnapshot.child("arterm").getValue(String.class);
                        String ad = dataSnapshot.child("ardef").getValue(String.class);
                        String et = dataSnapshot.child("enterm").getValue(String.class);
                        String ed = dataSnapshot.child("endef").getValue(String.class);
                        String imageUri = dataSnapshot.child("image").getValue(String.class);
                Link = dataSnapshot.child("video").getValue(String.class);

                //  myUri = Uri.parse(imageUri);
             //   URI myURI = new URI(imageUri);

             //   Picasso.with(this).load(imageUri).into(imageViewTerm);
              Picasso.get().load(imageUri).into(imageViewTerm);
             //   imageViewTerm.setImageURI(imageUri);
              Log.d("", " " + imageUri);


                //Log.d("test", "" + list);
                       // array_list = new ArrayList<>(list);
                        textView_arb_term.setText(at);
                        textView_arb_def.setText(ad);
                        textView_eng_term.setText(et);
                        textView_eng_def.setText(ed);
                   // }


                btn_vid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Link inside Button", "" + Link);
                        Intent intent = new Intent(details.this, videoPlayer.class);
                        intent.putExtra("URL", Link);
                        startActivity(intent);
                    }
                });

            }

            private String getFileExtension(Uri uri){
                ContentResolver cR = getContentResolver();
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                return mime.getExtensionFromMimeType(cR.getType(uri));

            }

           /* private  void uploadFile(){
                if(myUri != null){
                    StorageReference fileReference =

                }else{
                 //   Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();

                }
            }*/

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}