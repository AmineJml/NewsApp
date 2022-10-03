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
import java.util.StringTokenizer;

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

    //list
    ArrayList<String> news;

    //intent
    Intent popUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        rand = new Random();
        my_list = (ListView) findViewById(R.id.my_list);
        user = (TextView) findViewById(R.id.txtView_popUp_user) ;

        sharedPref();
      //  populate();
     // populateTest();
        populateFinal();
        //arL();
       clickOnList();


    }

    public void newArticle(View v) {
            Intent addNewArtile = new Intent(this, AddNewArticle.class);
            startActivity(addNewArtile);
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
            // how to insert item only once
            // how to select items by name
            // cursor reexplanation

            // Date currentTime = Calendar.getInstance().getTime();
            // String current = String.valueOf(currentTime);

            // Log.d("timee", String.valueOf(currentTime));
            my_list = findViewById(R.id.my_list);

            the_list = new ArrayList<String>();

            String test = "publishedAtTest";
            SQLiteDatabase newsdb = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);
            //newsdb.execSQL("CREATE Table IF NOT EXISTS finaTestTable (ID INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR, author VARCHAR, publishedAt VARCHAR, description VARCHAR)");
           // newsdb.execSQL("INSERT INTO finaTestTable(title, author, publishedAt, description) VALUES ('Magician', 'abracadabra', 'dum bada tes','The best magic tricks in the worls starts with abracadabra' )");
            // newsdb.execSQL("INSERT INTO finaTestTable(title, author, publishedAt, description) VALUES ('TUTUTU', 'train boy', ' 23 september', 'tututu is the fastest train in the world')");
            //  newsdb.execSQL("DELETE FROM finaTestTable where title = 'AA'");
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

                String name =c.getInt(id) + '.' +  c.getString(title)  ;
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

}
