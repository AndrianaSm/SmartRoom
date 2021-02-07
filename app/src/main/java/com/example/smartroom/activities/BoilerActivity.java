package com.example.smartroom.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartroom.R;

import java.util.Objects;

public class BoilerActivity extends AppCompatActivity {

    LinearLayout backLayout ;
    ConstraintLayout boilerBtn;
    ImageView redLight;
    TextView openCloseBoiler;
    TextView openCloseTextView;
    final String BOILER_MODE="BOILER_MODE";
    public ImageView quest;
    public String insctuctions="Για να ανάψετε/κλείσετε τον θερμοσίφωνα πατήστε το μεγάλο κουμπί στο κάτω μέρος της οθόνης";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boiler);
        Objects.requireNonNull(getSupportActionBar()).hide();

        backLayout = findViewById(R.id.backLayout);
        boilerBtn = findViewById(R.id.boilerButton);
        redLight = findViewById(R.id.red_light);
        openCloseBoiler = findViewById(R.id.openCloseBoiler);
        openCloseTextView = findViewById(R.id.openCloseTextView);
        quest =findViewById(R.id.questImg);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        Bundle b = getIntent().getExtras();
        boolean value = false; // or other values
        if(b != null){
            value = b.getBoolean("command");
            if(value==false) {
                updateSharedPref(false);
                redLight.setVisibility(View.INVISIBLE);
                openCloseBoiler.setText(R.string.openTheBoiler);
                openCloseTextView.setText(R.string.open);
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.boiler_close);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }else {
                updateSharedPref(true);
                redLight.setVisibility(View.VISIBLE);
                openCloseBoiler.setText(R.string.closeTheBoiler);
                openCloseTextView.setText(R.string.close);
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.boiler_open);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }

        }

        if(sharedPref.getBoolean(BOILER_MODE,false)){
            redLight.setVisibility(View.VISIBLE);
            openCloseBoiler.setText(R.string.closeTheBoiler);
            openCloseTextView.setText(R.string.close);
        }else {
            redLight.setVisibility(View.INVISIBLE);
            openCloseBoiler.setText(R.string.openTheBoiler);
            openCloseTextView.setText(R.string.open);

        }

        boilerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPref.getBoolean(BOILER_MODE,false)){
                    redLight.setVisibility(View.INVISIBLE);
                    openCloseBoiler.setText(R.string.openTheBoiler);
                    openCloseTextView.setText(R.string.open);
                    updateSharedPref(false);
                }else {
                    redLight.setVisibility(View.VISIBLE);
                    openCloseBoiler.setText(R.string.closeTheBoiler);
                    openCloseTextView.setText(R.string.close);

                    updateSharedPref(true);
                }
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

    private void updateSharedPref(Boolean mode) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(BOILER_MODE, mode);
        editor.commit();
    }
}