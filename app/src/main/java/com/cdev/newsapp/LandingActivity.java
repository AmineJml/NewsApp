package com.cdev.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class LandingActivity extends AppCompatActivity {
    String name;
    ListView my_list;
    Random rand;
    TextView user;
    //clickOnList
    String selectedItem;

    SharedPreferences shared;
    SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        rand = new Random();
        my_list = (ListView) findViewById(R.id.my_list);
        user = (TextView) findViewById(R.id.txtView_user) ;

        sharedPref();
        populate(5);
        clickOnList();


    }

    public void clickOnList(){
        Intent c = new Intent(this, popUpActivity.class);
        my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = (String) adapterView.getItemAtPosition(i);



                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();


                c.putExtra("key", selectedItem + "we made it");
                startActivity(c);

            }

        });


    }

    public void sharedPref()
    {
        shared = this.getSharedPreferences("com.lau.shared", Context.MODE_PRIVATE);
        myEdit = shared.edit();
        name = shared.getString("token_a", "");
        user.setText("Welcome " + name);

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
