package com.comida;

import android.app.Activity;
import android.os.Bundle;
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
        String name = bundle.getString("name");
        String rating = bundle.getString("average_ratings");
        String address = bundle.getString("area");
        String profileUrl = bundle.getString("image_url");

        // initialize view
        TextView txtName = (TextView) findViewById(R.id.name);
        TextView txtRating = (TextView) findViewById(R.id.average_ratings);
        TextView txtAddress = (TextView) findViewById(R.id.area);
        NetworkImageView movieImage = (NetworkImageView)findViewById(R.id.movieImage);

        //set values to view
        txtName.setText(name);
        txtAddress.setText(address);
        txtRating.setText(rating);

      //  NetworkImageView movieImage = (NetworkImageView) findViewById(R.id.thumbnail);
       movieImage.setDefaultImageResId(R.drawable.bc);
        movieImage.setImageUrl(profileUrl, AppController.getInstance().getImageLoader());



        Toast.makeText(getApplicationContext(),"name from second activity "+bundle.getString("name"), Toast.LENGTH_LONG).show();

    }
}
