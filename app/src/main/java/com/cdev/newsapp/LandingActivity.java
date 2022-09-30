package com.cdev.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    //sharedPref
    SharedPreferences shared;
    SharedPreferences.Editor myEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        rand = new Random();
        my_list = (ListView) findViewById(R.id.my_list);
        user = (TextView) findViewById(R.id.txtView_popUp_user) ;

        sharedPref();
       // populate(5);
       populateTest();
        clickOnList();


    }

    public void clickOnList(){
        Intent popUp = new Intent(this, popUpActivity.class);
        my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = (String) adapterView.getItemAtPosition(i);
                popUp.putExtra("key", selectedItem + "we made it");
                startActivity(popUp);

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

    public void populateTest()
    {
        try{

            ArrayList<String> news = new ArrayList<String>();

            SQLiteDatabase sql = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS students (first_name VARCHAR, last_name VARCHAR)");
            sql.execSQL("INSERT INTO students(first_name, last_name) VALUES ('John', 'Doe')");
            sql.execSQL("INSERT INTO students(first_name, last_name) VALUES ('Charbel', 'Daoud')");

            // sql.execSQL("DELETE FROM students where first_name = 'John'");
            //sql.execSQL("DELETE FROM students where first_name = 'Charbel'");
            Cursor c = sql.rawQuery("Select * from students", null);

            int fnameIndex = c.getColumnIndex("first_name");
            int lnameIndex = c.getColumnIndex("last_name");
            c.moveToFirst();

            while(c!= null){
                String name = c.getString(fnameIndex) + " " + c.getString(lnameIndex);
                news.add(name);
                c.moveToNext();
                Log.d("key", "i go there");

            }
            Log.d("i got out", "i go there");

            Log.d("arrayyyy","AA" + String.valueOf(news));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, news);
            my_list.setAdapter(adapter);

        }catch(Exception e){
            e.printStackTrace();
        }
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
