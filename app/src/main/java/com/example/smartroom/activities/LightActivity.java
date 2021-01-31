package com.example.smartroom.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.SharedPreferences;
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

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if(b != null){
            value = b.getString("command");
            if(value.equals("open")){
                openTheLights();
            }else if(value.contains("close")){
                closeTheLights();
            }
        }

        if(sharedPref.getBoolean(modeLight,false)){
            closeTheLights();
        }else {
            openTheLights();
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