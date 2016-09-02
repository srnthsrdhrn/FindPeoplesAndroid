package com.blackpanther.findpeople;

import android.app.ProgressDialog;
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
    String username, email,password,repassword;
    String REGISTER_URL="http://10.1.124.67:8080/register/";
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
                username= usernameet.getText().toString();
                email= emailet.getText().toString();
                password= passwordet.getText().toString();
                repassword= repasswordet.getText().toString();
                new RegisterThread().execute(REGISTER_URL,username,password,email,repassword);
            }
        });




    }
    class RegisterThread extends AsyncTask<String,Void,String>{
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog= new ProgressDialog(Register.this);
            progressDialog.setTitle("Connecting");
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            progressDialog.dismiss();
            Toast.makeText(Register.this,string,Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder s=new StringBuilder();
            URL url=null;
            try {

                s.append(URLEncoder.encode("username", "UTF-8"));
                s.append("=");
                s.append(URLEncoder.encode(strings[1],"UTF-8"));
                s.append("&");
                s.append(URLEncoder.encode("email", "UTF-8"));
                s.append("=");
                s.append(URLEncoder.encode(strings[2],"UTF-8"));
                s.append("&");
                s.append(URLEncoder.encode("password", "UTF-8"));
                s.append("=");
                s.append(URLEncoder.encode(strings[3],"UTF-8"));
                url=new URL(strings[0]);
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.connect();

                OutputStream op=conn.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(op,"UTF-8"));
                writer.write(s.toString());
                writer.flush();
                writer.close();
                conn.connect();
                InputStream inputStream = conn.getInputStream();
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
            return s.toString();


        }
    }
}
