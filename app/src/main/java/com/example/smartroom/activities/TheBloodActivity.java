package com.example.smartroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartroom.R;

import java.util.Objects;

public class TheBloodActivity extends AppCompatActivity {

    ConstraintLayout bloodSugar;
    ConstraintLayout bloodPressure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_blood);
        Objects.requireNonNull(getSupportActionBar()).hide();

        bloodSugar = findViewById(R.id.bloodSugerBtn);
        bloodPressure = findViewById(R.id.bloodPresureBtn);

        bloodSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TheBloodActivity.this, BloodSugarActivity.class));
            }
        });
        bloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TheBloodActivity.this, BloodPressureActivity.class));

            }
        });
    }
}