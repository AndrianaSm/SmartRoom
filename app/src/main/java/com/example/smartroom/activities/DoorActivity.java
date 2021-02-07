package com.example.smartroom.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.media.AudioManager;
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
    public ImageView quest;
    public String insctuctions="Για να ανοίξετε την πόρτα πατήστε το κουμπί στο κάτω και αριστερό μέρος της οθόνης όπου γράφει Άνοιξε\nΜόλις η πόρτα ανοίξει θα ακουστεί ένας ήχος για επιβεβαίωση.\nΕάν θέλετε να μιήσετε πατήστε παρατεταμένα για όση ώρα θέλετε να μιλήσετε το κουμπί στο κάτω και δεξί μέρος της οθόνης όπου λέει Μίλα.\nΜόλις το ακουστικό ανοίξει θα ακουστεί ένας ήχος.Αντίστοιχα μόλις απεέυθερώσετε το χέρι σας απότ ο κουμπί θα ακουστεί κανά ένας ήχος για να επιβεβεωθείτε ότι το ακουστικό έχει κλείσει.";



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
        quest =findViewById(R.id.questImg);

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if(b != null){
            value = b.getString("command");
            MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.open_door);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.start(); // no need to call prepare(); create() does that for you
        }

        openDoorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.suc);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you

            }
        });

        talkBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mic.setBackgroundResource(R.drawable.red_mic);
                    MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.openmic);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.start(); // no need to call prepare(); create() does that for you
                    return true;
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mic.setBackgroundResource(R.drawable.mic);
                    MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.closemic);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
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


        quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vma) {
                alertDialog(insctuctions);
            }
        });
    }
    public void alertDialog(String insctuctions) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage(insctuctions);
        dialog.setTitle("Οδηγίες");
        dialog.setPositiveButton("Κατάλαβα", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which) { }});
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

}