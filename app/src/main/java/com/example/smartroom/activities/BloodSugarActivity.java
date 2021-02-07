package com.example.smartroom.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroom.R;
import com.example.smartroom.fragments.BloodPressureFragment;
import com.example.smartroom.fragments.BloodSugarFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class BloodSugarActivity extends AppCompatActivity {

    LinearLayout backLayout;
    private static BloodSugarActivity bloodSugarActivity;
    public TextView bloodSugarTextView;
    public ImageView quest;
    public String insctuctions="Επιλέξτε άναμεσα στους χρήστες που βρίσκονται στο πάνω μέρος της οθόνης για να δείτε τις μετρήσεις του ζαχάρου.\nΣτην συνέχεια, θα εμφανιστούν όλες οι μετρήσεις στο κάτω μέρος την οθόνης";

    public static BloodSugarActivity getBloodSugarActivity() {
        return bloodSugarActivity;
    }

    public static void setBloodSugarActivity(BloodSugarActivity bloodSugarActivity) {
        BloodSugarActivity.bloodSugarActivity = bloodSugarActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar);
        Objects.requireNonNull(getSupportActionBar()).hide();

        bloodSugarTextView = findViewById(R.id.bloodSugarTextView);
        backLayout = findViewById(R.id.backLayout);
        BloodSugarActivity.setBloodSugarActivity(this);
        quest =findViewById(R.id.questImg);

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if(b != null) {
            value = b.getString("command");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        BloodSugarFragment bloodSugarFragment =(BloodSugarFragment)fragmentManager.findFragmentById(R.id.container_sugar);

        if(bloodSugarFragment == null) {
            bloodSugarFragment = BloodSugarFragment.newInstance(value,"blah");
            fragmentManager.beginTransaction().add(R.id.container_sugar,bloodSugarFragment).commit();
        }

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

    public void setTheName(String name,String type) {
        switch (name){
            case "A" :
                bloodSugarTextView.setText(type +"\n" +"Ανδριάνα");
                break;
            case "B" :
                bloodSugarTextView.setText(type +"\n" +"Σπύρος");
                break;

            case "C" :
                bloodSugarTextView.setText(type +"\n" +"Αιμιλία");
                break;
            default:
                bloodSugarTextView.setText(type +"\n" +name);
                break;

        }
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