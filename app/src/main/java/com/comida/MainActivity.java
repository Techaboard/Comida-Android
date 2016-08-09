package com.comida;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.comida.util.HomeActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

/*import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;*/

public class MainActivity extends     Activity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView btnLogin;
    private ProgressDialog progressDialog;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            //AccessToken.getCurrentAccessToken().getToken();

           // mCallbackManager = CallbackManager.Factory.create();
            AccessToken mAccessToken = AccessToken.getCurrentAccessToken();

            // Once I clear device memory as in the attached pictures, getCurrentAccessToken() will return null next time I open the application.

            if(mAccessToken == null)
            {
                Log.d("AccessToken", "Null");
            }
            else
            {
                Log.d("1- AccessToken", mAccessToken.getToken());
            }
            
            
            //AccessToken accessToken = loginResult.getAccessToken();
            String token = AccessToken.getCurrentAccessToken().getToken();
            Profile profile = Profile.getCurrentProfile();
            displayMessage(profile);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();
       // mCallbackManager = CallbackManager.Factory.create();
        AccessToken mAccessToken = AccessToken.getCurrentAccessToken();

        if(mAccessToken == null)
        {
            Log.d("AccessToken", "Null");
        }
        else
        {
            Log.d("1- AccessToken", mAccessToken.getToken());
        }


        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);
            }
        };

       // accessTokenTracker.startTracking();
        profileTracker.startTracking();
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        assert getActionBar() != null;
        getActionBar().hide();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        if(PrefUtils.getCurrentUser(MainActivity.this) != null){
            //Intent homeIntent = new Intent(MainActivity.this, LogoutActivity.class);
            Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        }
        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.comida",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    private void displayMessage(Profile profile){
        if(profile != null){
            //textView.setText(profile.getName());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();



        callbackManager=CallbackManager.Factory.create();

        loginButton= (LoginButton)findViewById(R.id.login_button);

        loginButton.setReadPermissions("public_profile", "email","user_friends");

        btnLogin= (TextView) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                loginButton.performClick();

                loginButton.setPressed(true);

                loginButton.invalidate();

                loginButton.registerCallback(callbackManager, mCallBack);

                loginButton.setPressed(false);

                loginButton.invalidate();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            progressDialog.dismiss();

            // App code
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),

                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {

                            Log.e("response: ", response + "");
                            try {
                                user = new User();

                                user.facebookID = object.getString("id").toString();
                                user.email = object.getString("email").toString();
                                user.name = object.getString("name").toString();
                                user.gender = object.getString("gender").toString();
                                PrefUtils.setCurrentUser(user,MainActivity.this);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            Toast.makeText(MainActivity.this,"Comida welcome "+user.name,Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                            //Intent intent=new Intent(MainActivity.this,LogoutActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            progressDialog.dismiss();
        }

        @Override
        public void onError(FacebookException e) {
            progressDialog.dismiss();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
