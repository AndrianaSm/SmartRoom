package com.example.smartroom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartroom.R;

import java.util.Objects;

public class BloodPressureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }
}