package com.blackpanther.findpeople;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button Login,Register,Forgot_Password;
    EditText usernameet,passwordet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        Login = (Button) findViewById(R.id.login);
        Register = (Button) findViewById(R.id.register);
        Forgot_Password = (Button) findViewById(R.id.forgot_password);
        usernameet = (EditText) findViewById(R.id.rusername);
        passwordet = (EditText) findViewById(R.id.rpassword);
        if (!(netInfo != null && netInfo.isConnected())) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.d("Network","Not Connected");
            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Toast.makeText(Login.this,"Not Connected",Toast.LENGTH_LONG).show();
                }
            });
            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Toast.makeText(Login.this,"Not Connected",Toast.LENGTH_LONG).show();

                }
            });
            Forgot_Password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Toast.makeText(Login.this,"Not Connected",Toast.LENGTH_LONG).show();

                }
            });
        } else {

            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences mySharedPreferences=getSharedPreferences("login_details",Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEditor=mySharedPreferences.edit();
                    myEditor.putBoolean("flag",true);
                    myEditor.putString("username",usernameet.getText().toString());
                    myEditor.putString("password",passwordet.getText().toString());
                    myEditor.commit();
                    Intent helperIntent=new Intent(getApplicationContext(),SplashScreenActivity.class);
                    startActivity(helperIntent);

                }
            });
            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Intent myIntent = new Intent(getApplicationContext(), Register.class);
                        startActivity(myIntent);

                }
            });
            Forgot_Password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                       Toast.makeText(Login.this,"your Password has been sent to your Email",Toast.LENGTH_LONG).show();
                }
            });
        }
    }



}
