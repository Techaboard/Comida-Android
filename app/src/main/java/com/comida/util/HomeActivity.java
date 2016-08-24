package com.comida.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.comida.ListViewActivity;
import com.comida.ListViewActivityDeals;
import com.comida.R;

//import android.widget.Toolbar;

public class HomeActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ImageView image = (ImageView) findViewById(R.id.test_image);


        // get the button view
        ImageView location = (ImageView) findViewById(R.id.location);
        // set a onclick listener for when the button gets clicked
        location.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(HomeActivity.this,
                        ListViewActivity.class);
                startActivity(mainIntent);
            }
        });

        ImageView deals = (ImageView) findViewById(R.id.deals);
        // set a onclick listener for when the button gets clicked
        deals.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent1 = new Intent(HomeActivity.this,
                        ListViewActivityDeals.class);
                startActivity(mainIntent1);
            }
        });
        ImageView cuisine = (ImageView) findViewById(R.id.cuisine);
        // set a onclick listener for when the button gets clicked
        cuisine.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent3 = new Intent(HomeActivity.this,
                        ListViewActivityCuisine.class);
                startActivity(mainIntent3);
            }
        });
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

}