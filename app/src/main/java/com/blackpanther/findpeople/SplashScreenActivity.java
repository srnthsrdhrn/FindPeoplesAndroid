package com.blackpanther.findpeople;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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

public class SplashScreenActivity extends AppCompatActivity{
        String baseurl;
        String LOGIN_URL = baseurl+"api-token-auth/";

        public boolean connected = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash_screen_layout);
            baseurl =  getResources().getString(R.string.baseURL);
            SharedPreferences mySharedPreferences = getSharedPreferences("login_details", Context.MODE_PRIVATE);
            if (!mySharedPreferences.getBoolean("flag", false)) {
                Intent loginIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(loginIntent);
            } else {
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
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(getQuery(strings[1], strings[2]));
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();


                    InputStream inputStream = new BufferedInputStream(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = reader.readLine();
                    try {
                        JSONObject tokenJson = new JSONObject(line);
                        final SharedPreferences sprefer=getSharedPreferences("login_details",Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit=sprefer.edit();
                        edit.putString("token",tokenJson.getString("token"));
                        edit.apply();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),sprefer.getString("token","hai"),Toast.LENGTH_LONG).show();
                            }
                        });
                    }catch (JSONException e){
                        Log.e("json",e.toString());
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

