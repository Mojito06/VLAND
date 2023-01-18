package com.example.vigiflash;

import static com.example.vigiflash.MainActivity.Jour1;
import static com.example.vigiflash.MainActivity.Jour2;
import static com.example.vigiflash.MainActivity.Jour3;
import static com.example.vigiflash.MainActivity.Jour4;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.*;
import java.net.*;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.LocalDate;

import androidx.appcompat.app.AppCompatActivity;

public class GreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.green_activity);

        // Changement du text dans le button pour afficher la date du jour
        Button button1 = findViewById(R.id.button_jour1);
        Button button2 = (Button)findViewById(R.id.button_jour2);
        Button button3 = (Button)findViewById(R.id.button_jour3);
        Button button4 = (Button)findViewById(R.id.button_jour4);

        button1.setText(Jour1.date);
        button2.setText(Jour2.date);
        button3.setText(Jour3.date);
        button4.setText(Jour4.date);
    }

    // Test du dvalue pour changer l'activité en fonction
    public void buttonJour1(View v) {
        if (Jour1.value == 1) {
            switchGreenActivities();
        }
        if (Jour1.value == 2) {
            switchYellowActivities();
        }
        if (Jour1.value == 3) {
            switchRedActivities();
        }
    }

    public void buttonJour2(View v) {
        if (Jour2.value == 1) {
            switchGreenActivities();
        }
        if (Jour2.value == 2) {
            switchYellowActivities();
        }
        if (Jour2.value == 3) {
            switchRedActivities();
        }
    }

    public void buttonJour3(View v) {
        if (Jour3.value == 1) {
            switchGreenActivities();
        }
        if (Jour3.value == 2) {
            switchYellowActivities();
        }
        if (Jour3.value == 3) {
            switchRedActivities();
        }
    }

    public void buttonJour4(View v) {
        if (Jour4.value == 1) {
            switchGreenActivities();
        }
        if (Jour4.value == 2) {
            switchYellowActivities();
        }
        if (Jour4.value == 3) {
            switchRedActivities();
        }
    }
    // changement d'activité
    private void switchGreenActivities() {
        //setContentView(R.layout.green_activity);
        Intent switchActivityIntent = new Intent(this, GreenActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchYellowActivities() {
        //setContentView(R.layout.yellow_activity);
        Intent switchActivityIntent = new Intent(this, YellowActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchRedActivities() {
        //setContentView(R.layout.red_activity);
        Intent switchActivityIntent = new Intent(this, RedActivity.class);
        startActivity(switchActivityIntent);
    }
}