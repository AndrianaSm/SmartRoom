package com.example.smartroom.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroom.R;

import java.util.Objects;

public class TempActivity extends AppCompatActivity {

    ImageView plusImg,minusImg;
    TextView tempText;
    LinearLayout backLayout ;
    final String TEMP = "TEMP";
    int temp;
    public ImageView quest;
    public String insctuctions="Για να αυξήσετε την θερμοκρασία πατήστε το πράσινο κουμπί\n.Αντίστοιχα για να μειώσετε την θερμοκρασία του δωματίου πατήστε το κόκκινο κουμπί";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        Objects.requireNonNull(getSupportActionBar()).hide();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        plusImg = findViewById(R.id.plusImage);
        minusImg = findViewById(R.id.minusImage);
        tempText = findViewById(R.id.tempText);
        backLayout = findViewById(R.id.backLayout);
        quest =findViewById(R.id.questImg);


        temp = sharedPref.getInt(TEMP,20);
        tempText.setText(temp+"°C");

        Bundle b = getIntent().getExtras();
        int value = 0; // or other values
        if(b != null){
            value = b.getInt("command");
            if(value>=16 && value<=30)
                tempText.setText(value + "°C");
            playMp3(value);
        }

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        plusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp <30) {
                    temp++;
                    tempText.setText(temp + "°C");
                    updateSharedPref(temp);
                }else {
                    Toast.makeText(TempActivity.this,"Δεν μπορείτε να ορίσετε την θερμοκρασία πάνω απο 30 βαθμούς κελσίου", Toast.LENGTH_SHORT).show();
                }
            }
        });
        minusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp >16) {
                    temp--;
                    tempText.setText(temp + "°C");
                    updateSharedPref(temp);
                }else {
                    Toast.makeText(TempActivity.this,"Δεν μπορείτε να ορίσετε την θερμοκρασία κάτω απο 16 βαθμούς κελσίου", Toast.LENGTH_SHORT).show();
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

    private void playMp3(int value) {
        MediaPlayer mediaPlayer = null;
        switch (value){
            case 16:
                 mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.seexteen);
                break;
            case 17:
                 mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.seventeen);
                break;
            case 18:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.eighteen);
                break;
            case 19:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.nineteen);
                break;
            case 20:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty);
                break;
            case 21:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty_one);
                break;
            case 22:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty_two);
                break;
            case 23:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty_three);
                break;
            case 24:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty_four);
                break;
            case 25:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty_five);
                break;
            case 26:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty_six);
                break;
            case 27:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty_seven);
                break;
            case 28:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty_eight);
                break;
            case 29:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.twenty_nine);
                break;
            case 30:
                mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.thirty);
                break;
            default:
                if(value<16)
                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.min_temp);
                else
                    mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.max_temp);
                break;
        }
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

    }

    public void alertDialog(String insctuctions) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage(insctuctions);
        dialog.setTitle("Οδηγίες");
        dialog.setPositiveButton("Κατάλαβα", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which) { }});
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    private void updateSharedPref(int temp) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(TEMP, temp);
        editor.commit();
    }

}