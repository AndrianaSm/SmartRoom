package com.example.smartroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
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

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
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
    }

    private void updateSharedPref(Boolean mode) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(BOILER_MODE, mode);
        editor.commit();
    }
}