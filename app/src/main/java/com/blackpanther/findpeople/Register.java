package com.blackpanther.findpeople;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText usernameet=(EditText) findViewById(R.id.rusername);
        final EditText emailet=(EditText) findViewById(R.id.remail);
        final EditText passwordet=(EditText)findViewById(R.id.rpassword);
        final EditText repasswordet=(EditText)findViewById(R.id.rpasswordrep);
        Button register=(Button)findViewById(R.id.rregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RegisterThread().execute(
                        usernameet.getText().toString(),
                        emailet.getText().toString(),
                        passwordet.getText().toString()
                );
            }
        });




    }
    class RegisterThread extends AsyncTask<String,Void,Integer>{

        @Override
        protected Integer doInBackground(String... strings) {
            StringBuilder s=new StringBuilder();
            URL url=null;
            try {

                s.append(URLEncoder.encode("username", "UTF-8"));
                s.append("=");
                s.append(URLEncoder.encode(strings[0],"UTF-8"));
                s.append("&");
                s.append(URLEncoder.encode("email", "UTF-8"));
                s.append("=");
                s.append(URLEncoder.encode(strings[1],"UTF-8"));
                s.append("&");
                s.append(URLEncoder.encode("password", "UTF-8"));
                s.append("=");
                s.append(URLEncoder.encode(strings[2],"UTF-8"));
                url=new URL("http://10.1.124.67:8080/register/");
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.connect();

                OutputStream op=(OutputStream)conn.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(op,"UTF-8"));
                writer.write(s.toString());
                writer.flush();
                writer.close();
                conn.connect();
                InputStream inputStream = (InputStream)conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                String data=null;
                while((line=reader.readLine())!=null){
                    data+=line;
                }
                inputStream.close();
                Log.w("Test1","adasdsda");
                conn.disconnect();


            }catch (Exception e){
                final String err=e.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),err,Toast.LENGTH_LONG).show();
                    }
                });


            }
            return 0;


        }
    }
}
