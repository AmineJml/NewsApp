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
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class LandingActivity extends AppCompatActivity {
    String name;
    ListView my_list;
    Random rand;
    TextView user;

    //populate
    ArrayList<String> the_list;
    ArrayAdapter<String> adapter;

    //clickOnList
    String selectedItem;

    //sharedPref
    SharedPreferences shared;
    SharedPreferences.Editor myEdit;

    ArrayList<String> news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        rand = new Random();
        my_list = (ListView) findViewById(R.id.my_list);
        user = (TextView) findViewById(R.id.txtView_popUp_user) ;

        sharedPref();
        populate();
     // populateTest();
        //populateFinal();
        //arL();
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

    public void populateFinal(){

        try{
            //how to insert item only once
            //how to select items by name
            //cursor reexplanation

           // Date currentTime = Calendar.getInstance().getTime();
         //   String current = String.valueOf(currentTime);

          //  Log.d("timee", String.valueOf(currentTime));
            my_list = findViewById(R.id.my_list);

            the_list = new ArrayList<String>();

            String test = "publishedAtTest";
            SQLiteDatabase newsdb = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);
            newsdb.execSQL("CREATE Table IF NOT EXISTS finaTestTable (ID INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR, author VARCHAR, publishedAt VARCHAR, description VARCHAR)");

            newsdb.execSQL("INSERT INTO finaTestTable(title, author, publishedAt, description) VALUES ('Magician', 'abracadabra', test,'The best magic tricks in the worls starts with abracadabra' )");
           // newsdb.execSQL("INSERT INTO finaTestTable(title, author, publishedAt, description) VALUES ('TUTUTU', 'train boy', ' 23 september', 'tututu is the fastest train in the world')");

            //newsdb.execSQL("DELETE FROM finaTestTable where title = 'AA'");
           // newsdb.execSQL("DELETE FROM finaTestTable where title = 'BB'");



            Cursor c = newsdb.rawQuery("Select * from finaTestTable", null);
            int id = c.getColumnIndex("ID");
            int title = c.getColumnIndex("title");
            int author = c.getColumnIndex("author");
            int publishedAt = c.getColumnIndex("publishedAt");
//---------------------------------------------------------------------------------------------
            c.moveToFirst();

            while(c!= null){
                int i = c.getInt(id);
                //Log.d("key1", String.valueOf(i));

                String name = c.getString(title) + " by " + c.getString(author) + " " + c.getString(publishedAt) + " " + c.getInt(id) ;
                the_list.add(name);
                c.moveToNext();
            }
            //why cant we add code here?  (adapter)
        }catch(Exception e){
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, the_list);
        my_list.setAdapter(adapter);

    }

    public void populateTest()
    {
        try{
            //how to insert item only once
            //how to select items by name
            //cursor reexplanation
            my_list = findViewById(R.id.my_list);

            the_list = new ArrayList<String>();
            SQLiteDatabase newsdb = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);

            newsdb.execSQL("CREATE Table IF NOT EXISTS news (title VARCHAR, author VARCHAR)");
            newsdb.execSQL("INSERT INTO news(title, author) VALUES ('AA', 'AA')");
            newsdb.execSQL("INSERT INTO news(title, author) VALUES ('BB', 'BB')");

            Cursor c = newsdb.rawQuery("Select * from news", null);

            int title = c.getColumnIndex("title");
            int author = c.getColumnIndex("author");

            c.moveToFirst();

            while(c!= null){
                String name = c.getString(title) + " by " + c.getString(author) + " " ;
                the_list.add(name);
                Log.d("key4", "problem here");
                c.moveToNext();
            }
        //why cant we add code here?  (adapter)
        }catch(Exception e){
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, the_list);
        my_list.setAdapter(adapter);

    }

    public void populate()
    {
        my_list = findViewById(R.id.my_list);
        the_list = new ArrayList<String>();
        //the_list = new ArrayList<String>(Arrays.asList("Mobile", "Web", "CP2"));

        the_list.add("Mobile Computing");
        the_list.add("CP1");
        the_list.add("Web");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, the_list);
        //allow us to add them to the list view
        my_list.setAdapter(adapter);

        //HELP

        my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_LONG).show();

            }
        });
    }


}
