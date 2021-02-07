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

import com.example.smartroom.R;

import java.util.Objects;

public class RollerActivity extends AppCompatActivity {

    private ConstraintLayout upBtn;
    private ConstraintLayout downBtn;
    private LinearLayout backLayout ;
    private ImageView rollerImg;
    private int state;
    public ImageView quest;
    public String insctuctions = "Για να ανοίξετε και να κλείσετετα ρολά στο δωμάτιο σας πατήστε το κουμπί που γράφει Ανέβασε και Κατέβασε αντίστοιχα.Υπάρχουν 4 επιλογές για την θέση.Ανεβοκατεβάστε την θέση μέχρι αν φτάσετε στο επιθειμητό αποτέλεσμα.";


    private final String ROLLER_STATE = "ROLLER_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roller);
        Objects.requireNonNull(getSupportActionBar()).hide();

        backLayout = findViewById(R.id.backLayout);
        upBtn = findViewById(R.id.openRollerBtn);
        downBtn = findViewById(R.id.closeRollerBtn);
        rollerImg = findViewById(R.id.rollerImg);
        quest =findViewById(R.id.questImg);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        state = sharedPref.getInt(ROLLER_STATE,-1);
        switchStates(state);

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if(b != null){
            value = b.getString("command");

            if(value.equals("άνοιξε")){
                updateSharedPref(0);
                switchStates(0);
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.roller_open);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }else {
                updateSharedPref(3);
                switchStates(3);
                MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.roller_close);
                mediaPlayer.start(); // no need to call prepare(); create() does that for you
            }
        }

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSharedPref(state-1);
                state =sharedPref.getInt(ROLLER_STATE,-1);
               switchStates(state);
            }
        });
        downBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSharedPref(state+1);
                state =sharedPref.getInt(ROLLER_STATE,-1);
                switchStates(state);
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

    private void switchStates(int state) {
        switch (state) {
            case 0:
                rollerImg.setBackgroundResource(R.drawable.roller_0);
                break;
            case 2:
                rollerImg.setBackgroundResource(R.drawable.roller_2);
                break;
            case 3:
                rollerImg.setBackgroundResource(R.drawable.roller_3);
                break;
            default :
                rollerImg.setBackgroundResource(R.drawable.roller_1);
                break;
        }

    }

    private void updateSharedPref(int state) {
        if(state>=0 && state<=3) {
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(ROLLER_STATE, state);
            editor.commit();
        }
    }
}