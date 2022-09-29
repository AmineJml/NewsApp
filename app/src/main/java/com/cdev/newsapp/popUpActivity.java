package com.cdev.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class popUpActivity extends AppCompatActivity {

    String name;
    TextView user;

    SharedPreferences shared;
    SharedPreferences.Editor myEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        sharedPref();
    }

    public void sharedPref()
    {
        shared = this.getSharedPreferences("com.lau.shared", Context.MODE_PRIVATE);
        myEdit = shared.edit();
        name = shared.getString("token_a", "");
        user.setText("Welcome " + name);

    }
}