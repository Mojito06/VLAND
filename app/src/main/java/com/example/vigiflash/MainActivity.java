package com.example.vigiflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.green_activity);
    }

    public void buttonJour1 (View v) {
        switchGreenActivities();
    }

    public void buttonJour2 (View v) {
        switchYellowActivities();
    }

    public void buttonJour3 (View v) {
        switchRedActivities();
    }

    private void switchGreenActivities() {
        Intent switchActivityIntent = new Intent(this, GreenActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchYellowActivities() {
        Intent switchActivityIntent = new Intent(this, YellowActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchRedActivities() {
        Intent switchActivityIntent = new Intent(this, RedActivity.class);
        startActivity(switchActivityIntent);
    }
}