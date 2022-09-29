package com.cdev.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     Button proceed;
     EditText name;
    SharedPreferences shared;
    SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setting shared pref with editor
        shared = this.getSharedPreferences("com.lau.shared", Context.MODE_PRIVATE);
        myEdit = shared.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proceed = (Button) findViewById(R.id.btn_proceed);
        name = (EditText) findViewById(R.id.inputText_name);

    }

    public void proceedToLanding(View v) {
        String str_name = name.getText().toString();
        if (str_name.equals("")){
            Toast.makeText(this, "please enter a valid user", Toast.LENGTH_LONG);
        } else {

            //addign token_a to the sahred pref
            myEdit.putString("token_a", str_name);
            myEdit.commit();
            String token = shared.getString("token_a", "");
           // Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();



            Intent i = new Intent(this, LandingActivity.class);

            startActivity(i);
        }
    }
}