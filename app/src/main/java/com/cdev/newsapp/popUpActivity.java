package com.cdev.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class popUpActivity extends AppCompatActivity {

    String name;
    TextView user;


    SharedPreferences shared;
    SharedPreferences.Editor myEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        user = (TextView) findViewById(R.id.txtView_popUp_user);


       sharedPref();


        Intent c = getIntent();
        String key = c.getStringExtra("key");
       Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();

    }

    public void sharedPref()
    {
        shared = this.getSharedPreferences("com.lau.shared", Context.MODE_PRIVATE);
        myEdit = shared.edit();
        name = shared.getString("token_a", "");
        user.setText("Welcome " + name);

    }
}