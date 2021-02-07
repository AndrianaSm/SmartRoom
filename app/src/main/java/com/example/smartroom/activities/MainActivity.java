package com.example.smartroom.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroom.R;
import com.example.smartroom.adapters.PressureBloodMeasurementsAdapter;

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
    public ImageView quest;
    public String insctuctions="Μπορείτε να δώσετε φωνητικές εντολές για να κάνετε οποιαδήποτε λειτουργία.Για παράδειγμα :\nΕντολή για τα φώτα : Άνοιξε τα φώτα.\nΕντολή για την θερμοκρασία : Άυξησε/Μείωσε/Θέσε την θερμοκρασία στους 25 βαθμούς.\nΕντολή για τον θερμοσίφωνα : Άνοιξε/κλείσε τον θερμοσίφωνα.\nΕντολή για την πόρτα : Άνοιξε την πόρτα.\nΕντολή για τα ρολά : Άνοιξε/Κλείσε τα ρολά.\nΕντολή για τις μετρήσεις : Δείξε μου της μετρήσεις υγείας για το ζάχαρο του Σπύρου.";



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
        quest =findViewById(R.id.questImg);

        quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vma) {
                alertDialog(insctuctions);
            }
        });

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
                    if (lampBoolOpen(result.get(0).toLowerCase())) {
                        startLightsActivity("open");
                    } else if (lampBoolClose(result.get(0).toLowerCase())) {
                        startLightsActivity("close");
                    } else if (lampBoolBoilerClose(result.get(0).toLowerCase())) {
                        startBoilerActivity(false);
                    } else if (lampBoolBoilerOpen(result.get(0).toLowerCase())) {
                        startBoilerActivity(true);
                    } else if (lampBoolTemp(result.get(0).toLowerCase())) {
                        startTempActivity(result.get(0));
                    } else if (rollerBoolOpen(result.get(0).toLowerCase())) {
                        startRollerActivity("άνοιξε");
                    } else if (rollerBoolClose(result.get(0).toLowerCase())) {
                        startRollerActivity("κλείσε");
                    }else if (doorOpenBool(result.get(0).toLowerCase())) {
                        startDoorActivity("ανοιξε");
                    }else if(healthBool(result.get(0).toLowerCase())) {
                        startHealthActivity(result.get(0));
                    }
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
    private void startBoilerActivity(Boolean arg) {
        Intent intent = new Intent(MainActivity.this, BoilerActivity.class);
        Bundle b = new Bundle();
        b.putBoolean("command", arg); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }
    private void startTempActivity(String arg) {

        String intValue = arg.replaceAll("[^0-9]", ""); // returns 123
        if(intValue!=null) {
            Intent intent = new Intent(MainActivity.this, TempActivity.class);
            Bundle b = new Bundle();
            b.putInt("command", Integer.parseInt(intValue)); //Your id
            intent.putExtras(b); //Put your id to your next Intent
            startActivity(intent);
        }
    }
    private void startRollerActivity(String arg) {

        Intent intent = new Intent(MainActivity.this, RollerActivity.class);
        Bundle b = new Bundle();
        b.putString("command", arg); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }
    private void startDoorActivity(String arg) {

        Intent intent = new Intent(MainActivity.this, DoorActivity.class);
        Bundle b = new Bundle();
        b.putString("command", arg); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }
    private void startHealthActivity(String arg) {
        arg = arg.toLowerCase();
        if(arg.contains("ζάχαρο")||arg.contains("ζάχαρου")||arg.contains("ζαχάρου")){
            if(arg.contains("σπύρο")||arg.contains("σπύρου")||arg.contains("σπύρος")){
                Intent intent = new Intent(MainActivity.this, BloodSugarActivity.class);
                Bundle b = new Bundle();
                b.putString("command", "B"); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }else if(arg.contains("αιμιλία")||arg.contains("αιμιλίας")){
                Intent intent = new Intent(MainActivity.this, BloodSugarActivity.class);
                Bundle b = new Bundle();
                b.putString("command", "C"); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }else if(arg.contains("ανδριάνα")||arg.contains("ανδριάνας")) {
                Intent intent = new Intent(MainActivity.this, BloodSugarActivity.class);
                Bundle b = new Bundle();
                b.putString("command", "A"); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        }else if (arg.contains("πίεση")){
            if(arg.contains("σπύρο")||arg.contains("σπύρου")||arg.contains("σπύρος")){
                Intent intent = new Intent(MainActivity.this, BloodPressureActivity.class);
                Bundle b = new Bundle();
                b.putString("command", "B"); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }else if(arg.contains("αιμιλία")||arg.contains("αιμιλίας")){
                Intent intent = new Intent(MainActivity.this, BloodPressureActivity.class);
                Bundle b = new Bundle();
                b.putString("command", "C"); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }else if(arg.contains("ανδριάνα")||arg.contains("ανδριάνας")) {
                Intent intent = new Intent(MainActivity.this, BloodPressureActivity.class);
                Bundle b = new Bundle();
                b.putString("command", "A"); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
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

    boolean lampBoolOpen (String result) {
        return ((result.contains("άνοιξε") || result.contains("άναψε")) && (result.contains("φώτα") || result.contains("φως")  || result.contains("λάμπα")||result.contains("διακόπτη")));
    }
    boolean lampBoolClose (String result) {
        return ((result.contains("κλείσε")|| result.contains("σβήσε")) &&(result.contains("φώτα") || result.contains("φως") || result.contains("λάμπα") ||result.contains("διακόπτη")));
    }
    boolean lampBoolBoilerOpen (String result) {
        return ((result.contains("Άναψε")||result.contains("άναψε")|| result.contains("άνοιξε")|| result.contains("ανοίξτε")) &&(result.contains("θερμοσίφωνα") || result.contains("νερό")));
    }
    boolean lampBoolBoilerClose (String result) {
        return ((result.contains("κλείσε") || result.contains("σβήσε")) && (result.contains("θερμοσίφωνας") || result.contains("νερό")));
    }
    boolean lampBoolTemp (String result) {
        return (( result.contains("βάλε")||result.contains("αύξησε") || result.contains("Αύξηση")|| result.contains("ανέβασε")|| result.contains("θέσε") || result.contains("αυξήστε") || result.contains("ορίζεται")|| result.contains("ορίζεται") || result.contains("μείωσε") || result.contains("μείωσε") || result.contains("βαλε")) && (result.contains("θέρμανση") || result.contains("θερμοκρασία")));
    }
    boolean rollerBoolOpen (String result) {
        return ((result.contains("ανέβασε") ||result.contains("άνοιξε") )&& (result.contains("παντζούρια") || result.contains("ρολά")));
    }
    boolean rollerBoolClose (String result) {
        return (( result.contains("κατέβασε")||result.contains("κλείσε")) && (result.contains("παντζούρια") || result.contains("ρολά")));
    }
    boolean doorOpenBool (String result) {
        return ((result.contains("Άνοιξε")||result.contains("άνοιξε"))&&result.contains("πόρτα"));
    }
    boolean healthBool (String result) {
        return (result.contains("ζάχαρο")||result.contains("ζαχάρου")||result.contains("πίεση")||result.contains("πίεσης")||result.contains("υγείας"));
    }
    }
