package com.example.smartroom.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartroom.R;
import com.example.smartroom.fragments.BloodPressureFragment;

import java.util.Objects;

public class BloodPressureActivity extends AppCompatActivity {

    LinearLayout backLayout;
    private static BloodPressureActivity bloodPressureActivity;
    public TextView bloodPressureTextView;
    public ImageView quest;
    public String insctuctions="Επιλέξτε άναμεσα στους χρήστες που βρίσκονται στο πάνω μέρος της οθόνης για να δείτε τις μετρήσεις για την πίεση.\nΣτην συνέχεια, θα εμφανιστούν όλες οι μετρήσεις στο κάτω μέρος την οθόνης";

    public static BloodPressureActivity getBloodPressureActivity() {
        return bloodPressureActivity;
    }

    public static void setBloodPressureActivity(BloodPressureActivity bloodPressureActivity) {
        BloodPressureActivity.bloodPressureActivity = bloodPressureActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);
        Objects.requireNonNull(getSupportActionBar()).hide();

        bloodPressureTextView = findViewById(R.id.bloodPressureTextView);
        backLayout = findViewById(R.id.backLayout);
        BloodPressureActivity.setBloodPressureActivity(this);
        quest =findViewById(R.id.questImg);

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if(b != null) {
            value = b.getString("command");

        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        BloodPressureFragment bloodPressureFragment =(BloodPressureFragment)fragmentManager.findFragmentById(R.id.container_pressures);

        if(bloodPressureFragment == null) {
            bloodPressureFragment = BloodPressureFragment.newInstance(value,"blah");
            fragmentManager.beginTransaction().add(R.id.container_pressures,bloodPressureFragment).commit();
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
    public void alertDialog(String insctuctions) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage(insctuctions);
        dialog.setTitle("Οδηγίες");
        dialog.setPositiveButton("Κατάλαβα", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which) { }});
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
    public void setTheName(String name,String type) {
        switch (name){
            case "A":
                bloodPressureTextView.setText(type +"\n" +"Ανδριάνα");
                break;
            case "B":
                bloodPressureTextView.setText(type +"\n" +"Σπυρος");
                break;
            case "C":
                bloodPressureTextView.setText(type +"\n" +"Αιμιλία");
                break;
            default:
                bloodPressureTextView.setText(type +"\n" +name);
                break;

        }

    }

}