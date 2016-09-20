package com.blackpanther.findpeople;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


public class SplashScreenActivity extends AppCompatActivity {
        String LOGIN_URL = "http://54.244.177.52:8000/login/";

        public boolean connected = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.splash_screen_layout);
            SharedPreferences mySharedPreferences = getSharedPreferences("login_details", Context.MODE_PRIVATE);
            if (!mySharedPreferences.getBoolean("flag", false)) {
                Log.w("test","hai ");
                Intent loginIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(loginIntent);
                //Toast.makeText(getApplicationContext(), "dasda", Toast.LENGTH_LONG).show();
            } else {
                Log.w("test","hai ");
                new LoginConnect().execute(LOGIN_URL, mySharedPreferences.getString("username", "zzz"), mySharedPreferences.getString("password", "zzzz"));
            }


        }

        private class LoginConnect extends AsyncTask<String, Void, String> {
            ProgressDialog progressDialog;
            HttpURLConnection conn;

            @Override
            protected String doInBackground(String... strings) {
                String data = "";
                final String temp1 = strings[1];
                final String temp2 = strings[2];
                try {
                    URL url = new URL(strings[0]);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SplashScreenActivity.this, temp1 + " " + temp2, Toast.LENGTH_LONG).show();
                        }
                    });

                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(getQuery(strings[1], strings[2]));
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();


                    InputStream inputStream = new BufferedInputStream(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        data += line;
                    }
                    inputStream.close();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SplashScreenActivity.this, " URL error Exception", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(SplashScreenActivity.this, "IOException", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                return data;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(SplashScreenActivity.this);
                progressDialog.setTitle("Connecting");
                progressDialog.setMessage("Please Wait");
                progressDialog.setCancelable(false);
                progressDialog.show();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                connected = true;
                Map<String, List<String>> myMap = conn.getHeaderFields();
                List<String> myList = myMap.get("Set-Cookie");
                final String[] session = myList.get(1).split(";");
                final String[] sessionid = session[0].split("=");


                Log.w("cba", sessionid[1]);
                String[] temp = session[0].split("=");
                SharedPreferences preferences = getSharedPreferences("login_details", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("sessionid", temp[1]);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences mySharedPreferences = getSharedPreferences("login_details", Context.MODE_PRIVATE);
                        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
                        myEditor.putString("sessionid", sessionid[1]);
                        myEditor.apply();
                        Log.w("cba", mySharedPreferences.getString("sessionid", "asdsa"));
                    }
                });
                Intent homeIntent = new Intent(getApplicationContext(), Homepage.class);
                startActivity(homeIntent);
            }

            private String getQuery(String username, String password) throws UnsupportedEncodingException {

                return "&" +
                        URLEncoder.encode("username", "UTF-8") +
                        "=" +
                        URLEncoder.encode(username, "UTF-8") +
                        "&" +
                        URLEncoder.encode("password", "UTF-8") +
                        "=" +
                        URLEncoder.encode(password, "UTF-8");
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                if (progressDialog != null)
                    progressDialog.dismiss();
            }
        }

}

