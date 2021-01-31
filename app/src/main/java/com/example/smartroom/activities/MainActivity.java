package com.example.smartroom.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroom.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private LinearLayout lightBtn;
    private LinearLayout tempBtn;
    private LinearLayout boilerBtn;
    private LinearLayout doorBtn;
    private LinearLayout healthBtn;
    LinearLayout roller;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        txtSpeechInput =  findViewById(R.id.textViewSmartRoom);
        btnSpeak =  findViewById(R.id.button);
        lightBtn = findViewById(R.id.lightLayout);
        tempBtn = findViewById(R.id.buttonTemp);
        boilerBtn = findViewById(R.id.buttonBoiler);
        doorBtn = findViewById(R.id.buttonIntercom);
        healthBtn = findViewById(R.id.buttonHealth);
        roller = findViewById(R.id.roller);

        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LightActivity.class));
            }
        });
        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TempActivity.class));
            }
        });
        boilerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BoilerActivity.class));
            }
        });
        doorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DoorActivity.class));
            }
        });
        healthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TheBloodActivity.class));
            }
        });

        roller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RollerActivity.class));

            }
        });

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //TODO:: SPEECH TO TEXT
                    if(result.get(0).contains("άνοιξε") && result.get(0).contains("φώτα")) {
                        startLightsActivity("open");
                    }else if (result.get(0).contains("Κλείσε") && result.get(0).contains("φώτα")) {
                        startLightsActivity("close");
                    }else if(result.get(0).contains("Κλείσε") && result.get(0).contains("θερμοσίφωνα")) {
                        startBoilerActivity("Κλείσε");
                    }else if(result.get(0).contains("άνοιξε") && result.get(0).contains("θερμοσίφωνα")) {
                        startBoilerActivity("άνοιξε");

                    }
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }
        }
    }

    private void startLightsActivity(String arg) {
        Intent intent = new Intent(MainActivity.this, LightActivity.class);
        Bundle b = new Bundle();
        b.putString("command", arg); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }
    private void startBoilerActivity(String arg) {
        Intent intent = new Intent(MainActivity.this, LightActivity.class);
        Bundle b = new Bundle();
        b.putString("command", arg); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }
}
