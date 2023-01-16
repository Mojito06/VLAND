package com.example.vigiflash;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import java.io.*;
import java.net.*;
import org.json.JSONObject;
import org.json.JSONArray;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("coucouc");
        try {
            TestAsyncTask testAsyncTask = new TestAsyncTask(MainActivity.this, "http://isis.unice.fr/~pl105229/ext/sae302/db.php");
            testAsyncTask.execute();

        }
        catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("bruh");

        }
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

    public class TestAsyncTask extends AsyncTask<Void, Void, String> {
        private Context mContext;
        private String mUrl;

        public TestAsyncTask(Context context, String url) {
            mContext = context;
            mUrl = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String resultString = "Done";
            getJSON(mUrl);

            return resultString;
        }

        @Override
        protected void onPostExecute(String strings) {
            super.onPostExecute(strings);
        }

        private void getJSON(String url) {
            try {
                //Connexion au site
                URL the_url = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) the_url.openConnection();
                conn.setRequestMethod("GET");
                System.out.println("get");
                conn.setRequestProperty("Accept", "application/json");
                System.out.println("part1");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }
                // Création du buffer pour lire le contenue du site
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                System.out.println("buffer");
                StringBuilder sb = new StringBuilder();
                System.out.println("stringbuilder");
                String output;
                System.out.println("part3");
                // On crée une liste "sb" ou on vient rajouter les infos du site.
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                conn.disconnect();
                System.out.println("disconnect");
                // On crée un variable jsonText qui va nous permettre d'exploiter le JSON.
                String jsonText = sb.toString();

                JSONObject json = new JSONObject(jsonText);

                // On récupére Le jour et le day value de la chaine de charactère jsonText
                JSONArray signals = json.getJSONArray("signals");
                for (int i = 0; i < signals.length(); i++) {
                    JSONObject signal = signals.getJSONObject(i);
                    String jour = signal.getString("jour");
                    int dvalue = signal.getInt("dvalue");
                    System.out.println("Jour: " + jour + ", alerte niveau : " + dvalue);
                }
            }
            catch(Exception ex) {
                ex.printStackTrace();
                System.out.println("bruh");

            }

        }
    }
}