package com.cdev.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class LandingActivity extends AppCompatActivity {
    String name;
    ListView my_list;
    Random rand;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("message");

        rand = new Random();
        my_list = (ListView) findViewById(R.id.my_list);
        user = (TextView) findViewById(R.id.txtView_user) ;

        user.setText("Welcome" + name);
        populate(5);
    }
    public void populate(int x)
    {
        Log.d("inside", "IM INSEDESAFR");
        ArrayList<String> random_nums = new ArrayList<String>();
        double random_value = 0;
        for(int i =0; i <x; i++)
        {
            DecimalFormat formatter = new DecimalFormat(".##");
            random_value = rand.nextDouble();
            random_nums.add(formatter.format(random_value));

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, random_nums);
        my_list.setAdapter(adapter);
    }


}
