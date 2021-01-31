package com.example.smartroom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartroom.R;

import java.util.Objects;

public class TempActivity extends AppCompatActivity {

    ImageView plusImg,minusImg;
    TextView tempText;
    LinearLayout backLayout ;
    final String TEMP = "TEMP";
    int temp;

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


        temp = sharedPref.getInt(TEMP,20);
        tempText.setText(temp+"°C");

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        plusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp++;
                tempText.setText(temp+"°C");
                updateSharedPref(temp);
            }
        });
        minusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp--;
                tempText.setText(temp+"°C");
                updateSharedPref(temp);
            }
        });
    }

    private void updateSharedPref(int temp) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(TEMP, temp);
        editor.commit();
    }

}