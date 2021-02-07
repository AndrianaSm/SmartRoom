package com.example.smartroom.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smartroom.R;

import java.util.Objects;

public class TheBloodActivity extends AppCompatActivity {

    ConstraintLayout bloodSugar;
    ConstraintLayout bloodPressure;
    LinearLayout backLayout;
    public ImageView quest;
    public String insctuctions = "Επιλέξτε ποία μέτρηση σας ενδιαφέρει να δείτε.\nΠατήστε το κουμπί \"Πίεση\" για τις μετρήσεις της πίεσης και το κουμπί \"Ζάχαρο\" για τις μετρήσεις ζαχάρου.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_blood);
        Objects.requireNonNull(getSupportActionBar()).hide();

        bloodSugar = findViewById(R.id.bloodSugerBtn);
        bloodPressure = findViewById(R.id.bloodPresureBtn);
        backLayout = findViewById(R.id.backLayout);
        quest =findViewById(R.id.questImg);
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
}