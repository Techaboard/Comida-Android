package com.comida;

import android.app.Activity;
import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.comida.app.AppController;

/**
 * Created by techaboard user on 26/07/2016.
 */

public class SecondActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        // get value from intent
        Bundle bundle = getIntent().getExtras();
        String name = "Name:"+"\t"+bundle.getString("name");
        String rating = "Rating:"+"\t"+bundle.getString("average_ratings");
        String address = "Address:"+"\t"+bundle.getString("full_address");
        String profileUrl =bundle.getString("image_url");
        String cuisine ="Cuisine: "+"\t"+bundle.getString("cuisine");
        String cost ="Cost for Two:"+"\t"+"₹"+"\t"+bundle.getString("cost");

        // initialize view
        TextView txtName = (TextView) findViewById(R.id.name);
        TextView txtRating = (TextView) findViewById(R.id.average_ratings);
        TextView txtAddress = (TextView) findViewById(R.id.area);
        TextView txtcuisine= (TextView) findViewById(R.id.cuisine);
        TextView txtcost=(TextView) findViewById(R.id.cost);
        NetworkImageView movieImage = (NetworkImageView)findViewById(R.id.movieImage);

        //set values to view
        txtName.setText(name);
        txtAddress.setText(address);
        txtRating.setText(rating);
        txtcuisine.setText(cuisine);
        txtcost.setText(cost);
      //  NetworkImageView movieImage = (NetworkImageView) findViewById(R.id.thumbnail);
       movieImage.setDefaultImageResId(R.drawable.bc);
        movieImage.setImageUrl(profileUrl, AppController.getInstance().getImageLoader());



       // Toast.makeText(getApplicationContext(),"name from second activity "+bundle.getString("name"), Toast.LENGTH_LONG).show();

    }
}
