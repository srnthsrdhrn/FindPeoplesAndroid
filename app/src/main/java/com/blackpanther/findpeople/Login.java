package com.blackpanther.findpeople;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {
    Button Login,Register,Forgot_Password;
    EditText usernameet,passwordet;
    String username,password;
    String LOGIN_URL="http://10.1.124.67:8080/login/", REGISTER_URL="http://10.1.124.67:8080/register/", FORGOT_PASSWORD_URL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login = (Button) findViewById(R.id.login);
        Register = (Button) findViewById(R.id.register);
        Forgot_Password = (Button) findViewById(R.id.forgot_password);
        usernameet = (EditText) findViewById(R.id.rusername);
        passwordet = (EditText) findViewById(R.id.rpassword);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new connect().execute(LOGIN_URL,usernameet.getText().toString(),passwordet.getText().toString());
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(getApplicationContext(),Register.class);
                startActivity(myIntent);

            }
        });
        Forgot_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new connect().execute(FORGOT_PASSWORD_URL);
            }
        });
    }
    private class connect extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String data="";
            try {
                URL url = new URL(strings[0]);
                final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(strings[1],strings[2]));
                writer.flush();
                writer.close();
                os.close();
                conn.connect();


                InputStream inputStream = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while((line=reader.readLine())!=null){
                    data+=line;
                }
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this," URL error Exception",Toast.LENGTH_LONG).show();
                    }
                });
            } catch( IOException e){
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this,"IOException",Toast.LENGTH_LONG).show();
                    }
                });
            }
            return data;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(Login.this,s,Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }
        private String getQuery(String username,String password) throws UnsupportedEncodingException
        {
            String result = "&" +
                    URLEncoder.encode("username", "UTF-8") +
                    "=" +
                    URLEncoder.encode(username, "UTF-8") +
                    "&" +
                    URLEncoder.encode("password", "UTF-8") +
                    "=" +
                    URLEncoder.encode(password, "UTF-8");

            return result;
        }
    }
}
