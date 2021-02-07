package com.example.smartroom.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartroom.R;

import java.util.Objects;

public class LightActivity extends AppCompatActivity {

    TextView openCloseTextVIew;
    ImageView lightImageView ;
    TextView openCloseLamp;
    ConstraintLayout openCloseConstraintLayout;
    LinearLayout backLayout ;
    SharedPreferences sharedPref;
    final String modeLight = "MODE_LIGHT";
    public ImageView quest;
    public String insctuctions="Για να ανάψετε/κλείσετε το φώς πατήστε το μεγάλο κουμπί στο κάτω μέρος της οθόνης.";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        Objects.requireNonNull(getSupportActionBar()).hide();

        openCloseTextVIew = findViewById(R.id.openCloseTextView);
        lightImageView = findViewById(R.id.lightImage);
        openCloseConstraintLayout = findViewById(R.id.lightButton);
        backLayout = findViewById(R.id.backLayout);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        openCloseLamp = findViewById(R.id.openCloseBoiler);
        quest =findViewById(R.id.questImg);

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if(b != null){
            value = b.getString("command");
            if(value.equals("open")){
                openTheLights();
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.open_lights);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }else if(value.contains("close")){
                closeTheLights();
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.close_lights);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }
        }

        if(sharedPref.getBoolean(modeLight,false)){
            openTheLights();
        }else {
            closeTheLights();
        }
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        openCloseConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPref.getBoolean(modeLight,false)){
                   closeTheLights();
                }else {
                  openTheLights();
                }
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
    private void updateSharedPref(Boolean switcher) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(modeLight, switcher);
        editor.commit();
    }

    private void openTheLights() {
        lightImageView.setVisibility(View.VISIBLE);
        openCloseTextVIew.setText(R.string.close);
        openCloseLamp.setText(R.string.closeTheLamp);
        updateSharedPref(true);
    }

    private void closeTheLights() {
        lightImageView.setVisibility(View.INVISIBLE);
        openCloseTextVIew.setText(R.string.open);
        openCloseLamp.setText(R.string.openTheLamp);
        updateSharedPref(false);
    }
}