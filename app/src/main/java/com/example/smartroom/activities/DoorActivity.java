package com.example.smartroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroom.R;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DoorActivity extends AppCompatActivity  {
    LinearLayout backLayout;
    ConstraintLayout openDoorBtn, talkBtn;
    ImageView mic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);
        Objects.requireNonNull(getSupportActionBar()).hide();

        backLayout = findViewById(R.id.backLayout);
        openDoorBtn = findViewById(R.id.openDoorBtn);
        talkBtn = findViewById(R.id.talkBtn);
        mic = findViewById(R.id.micImg);
        mic.setBackgroundResource(R.drawable.mic);
        openDoorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.suc);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }
        });

        talkBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mic.setBackgroundResource(R.drawable.red_mic);
                    MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.openmic);
                    mediaPlayer.start(); // no need to call prepare(); create() does that for you
                    return true;
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mic.setBackgroundResource(R.drawable.mic);
                    MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.closemic);
                    mediaPlayer.start(); // no need to call prepare(); create() does that for you
                }
                return false;
            }
        });



        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}