package com.comida.util;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.comida.R;

/**
 * Created by techaboard user on 06/08/2016.
 */
public class HomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView image = (ImageView) findViewById(R.id.test_image);
    }
}