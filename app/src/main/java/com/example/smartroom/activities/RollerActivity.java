package com.example.smartroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
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
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        state = sharedPref.getInt(ROLLER_STATE,-1);
        switchStates(state);

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