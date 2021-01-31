package com.example.smartroom.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartroom.R;
import com.example.smartroom.fragments.SugarBloodFragment;

import java.util.Objects;

public class BloodSugarActivity extends AppCompatActivity {

    LinearLayout backLayout;
    private static BloodSugarActivity bloodSugarActivity;
    public TextView nameHealth ;

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        SugarBloodFragment sugarBloodFragment = (SugarBloodFragment) fragmentManager.findFragmentById(R.id.container_helath);

        nameHealth = findViewById(R.id.bloodSugarTextView);
        backLayout = findViewById(R.id.backLayout);
        BloodSugarActivity.setBloodSugarActivity(this);

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if(sugarBloodFragment == null) {
            sugarBloodFragment = SugarBloodFragment.newInstance("blah","blah");
            fragmentManager.beginTransaction().add(R.id.container_helath, sugarBloodFragment).commit();
        }
    }

    public void setTheName(String name,String type) {
        nameHealth.setText(type +"\n" +name);
    }
}