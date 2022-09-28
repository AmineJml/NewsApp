package com.cdev.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            Intent i = new Intent(this, LandingActivity.class);
            i.putExtra("message", str_name);
            startActivity(i);
        }
    }
}