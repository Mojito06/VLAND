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
import java.time.LocalDate;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static Jour Jour1 = new Jour();
    public static Jour Jour2 = new Jour();
    public static Jour Jour3 = new Jour();
    public static Jour Jour4 = new Jour();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(Jour1.getValue());
        try {
            System.out.println(Jour1.getValue());
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

                Jour1.value = day1.getInt("dvalue");
                Jour2.value = day2.getInt("dvalue");
                Jour3.value = day3.getInt("dvalue");
                Jour4.value = day4.getInt("dvalue");

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
                //for (int i = 0; i < signals.length(); i++) {
                    //JSONObject signal = signals.getJSONObject(i);
                //  String date = signal.getString("jour");
                //   String jour = date.substring(0, 10);
                //  int dvalue = signal.getInt("dvalue");
                //  LocalDate today = LocalDate.now();
                    ///System.out.println("Jour: " + jour + ", alerte niveau : " + dvalue);

                //   if (jour.equals("2023-01-04")) {
                        //       dvalues[0] = dvalue;
                //   jours[0] = jour;
                //    System.out.println("l'alerte est de niveau : " + dvalues[0] + " pour le jour : " + jours[0]);
                // }
                // if (jour.equals("2023-01-05")) {
                //     dvalues[1] = dvalue;
                //      jours[1] = jour;
                //      System.out.println("l'alerte est de niveau : " + dvalues[1] + " pour le jour : " + jours[1]);
                //   }
                //   if (jour.equals("2023-01-06")) {
                //      dvalues[2] = dvalue;
                //      jours[2] = jour;
                ////      System.out.println("l'alerte est de niveau : " + dvalues[2] + " pour le jour : " + jours[2]);
                //}
                //  if (jour.equals("2023-01-07")) {
                //      dvalues[3] = dvalue;
                //      jours[3] = jour;
                //      System.out.println("l'alerte est de niveau : " + dvalues[3] + " pour le jour : " + jours[3]);
                //  }
                //  ///if (jour.equals(today)) {
                    ///    dvalues[0] = dvalue;
                    ///    jour1 = jour;
                    ///    System.out.println("l'alerte est de niveau : " + dvalue1 + " pour le jour : " + jour1);
                    ///}
                // }

            }
            catch(Exception ex) {
                ex.printStackTrace();
                System.out.println("bruh");

            }

        }

    }
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