package com.example.vigiflash;

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

public class MainActivity extends AppCompatActivity {

    //Creation des objets pour chaque jours.
    public static Jour Jour1 = new Jour();
    public static Jour Jour2 = new Jour();
    public static Jour Jour3 = new Jour();
    public static Jour Jour4 = new Jour();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Lancement de la requête sur le site pour récupérer les données JSON
        try {
            TestAsyncTask testAsyncTask = new TestAsyncTask(MainActivity.this, "http://isis.unice.fr/~pl105229/ext/sae302/db.php");
            testAsyncTask.execute();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
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
                JSONObject day1 = signals.getJSONObject(0);
                JSONObject day2 = signals.getJSONObject(1);
                JSONObject day3 = signals.getJSONObject(2);
                JSONObject day4 = signals.getJSONObject(3);

                // On donne la valeur du dvalue qui correspond a chaque jour
                Jour1.value = day1.getInt("dvalue");
                Jour2.value = day2.getInt("dvalue");
                Jour3.value = day3.getInt("dvalue");
                Jour4.value = day4.getInt("dvalue");

                // On donne la date qui correspond au bon jour
                String date1 = day1.getString("jour");
                String theDate1 = date1.substring(0, 10);
                String date2 = day2.getString("jour");
                String theDate2 = date2.substring(0, 10);
                String date3 = day3.getString("jour");
                String theDate3 = date3.substring(0, 10);
                String date4 = day4.getString("jour");
                String theDate4 = date4.substring(0, 10);

                Jour1.date = theDate1;
                Jour2.date = theDate2;
                Jour3.date = theDate3;
                Jour4.date = theDate4;

                // Test du dvalue pour changer l'activité en fonction
                if (Jour1.value == 1) {
                    System.out.println(Jour1.value);
                    switchGreenActivities();
                }
                if (Jour1.value == 2) {
                    switchYellowActivities();
                }
                if (Jour1.value == 3) {
                    switchRedActivities();
                }
            }
            catch(Exception ex) {
                ex.printStackTrace();

            }

        }

    }
        // Même chose pour chaque buttton
    public void buttonJour1 (View v) {
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

    public void buttonJour2 (View v) {
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

    public void buttonJour3 (View v) {
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

    public void buttonJour4 (View v) {
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