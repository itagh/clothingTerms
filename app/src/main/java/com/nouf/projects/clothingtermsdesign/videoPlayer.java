package com.nouf.projects.clothingtermsdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class videoPlayer extends AppCompatActivity {


    VideoView video ;
    String link ="";
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);


        video = (VideoView) findViewById(R.id.videoView);
        Intent intent = getIntent();
        link = getIntent().getExtras().getString("URL", "defaultValue");
        Log.d("test","Link inside video player " +link);

        uri = Uri.parse(link);
        Log.d("test","URI inside video player " +uri);
        video.setVideoURI(uri);
        video.start();


    }

}
