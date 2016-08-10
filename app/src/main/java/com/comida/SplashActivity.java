package com.comida;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.Random;

public class SplashActivity extends Activity
{

    protected int _splashTime = 3000;
    int secondsDelayed = 2;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        //   this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_activity);

  //      assert getActionBar() != null;
//        getActionBar().hide();

        //      View mContentView = findViewById(R.id.fullscreen_content);
//        mContentView.setSystemUiVisibility(View.GONE | View.SYSTEM_UI_FLAG_FULLSCREEN);
//***For radndomise splash screen

        int[] yourListOfImages= {R.drawable.splash1, R.drawable.splash1, R.drawable.splash1};

        Random random = new Random(System.currentTimeMillis());
        int posOfImage = random.nextInt(yourListOfImages.length - 1);
        img=(ImageView)findViewById(R.id.img);
        img.setBackgroundResource(yourListOfImages[posOfImage]);
        //img.setImageResource(R.drawable.splashscreen);
//For radndomise splash screen***

        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent HomeScreen = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(HomeScreen);
                overridePendingTransition(0, 0);
                finish();

            }
        }, secondsDelayed * _splashTime);
    }
}