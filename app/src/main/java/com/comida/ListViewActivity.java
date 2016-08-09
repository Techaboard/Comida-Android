package com.comida;

/**
 * Created by techaboard user on 21/07/2016.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.comida.adater.CustomListAdapter;
import com.comida.app.AppController;
import com.comida.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends Activity {
    // Log tag
    private static final String TAG = ListViewActivity.class.getSimpleName();
    // change here url of server api
    private static final String url = "http://5d986898.ngrok.io/api/v1/restaurants/get_featured_restaurants";

    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;

    private CustomListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movieList.get(position);
                Intent intent = new Intent(ListViewActivity.this, SecondActivity.class);
                intent.putExtra("name", movie.getName());
                intent.putExtra("average_ratings", movie.getAverage_ratings());
                intent.putExtra("full_address", movie.getAddress());
                intent.putExtra("image_url", movie.getThumbnailUrl());
                intent.putExtra("cuisine",movie.getCuisine());
                intent.putExtra("cost",movie.getCost());
                startActivity(intent);
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Movie movie = movieList.get(position);
                // retrieve from movie whatever you want
                movie.getName();

                Intent intent = new Intent(ListViewActivity.this,SecondActivity.class);
               TextView name = (TextView) findViewById(R.id.name);
              //  name.setText(movie.getName());



                intent.putExtra("name",movie.getName());
                intent.putExtra("url",movie.getThumbnailUrl());
                intent.putExtra("rating",movie.getAverage_ratings());
                intent.putExtra("add",movie.getAddress());
                intent.putExtra("cusine",movie.getCuisine());
                startActivity(intent);*/



                /*intent.putExtra("name",movie.getName());
                String name1 = intent.getStringExtra("name");
                name.setText(name1);
                startActivity(intent);
                intent.putExtra("param1",movie.getName());
                intent.putExtra("param2",movie.getName());
                intent.putExtra("param3",movie.getName());*/
                //Toast.makeText(getApplicationContext(),"Name: "+movie.getName(), Toast.LENGTH_LONG).show();
            }
        });
        listView.setAdapter(adapter);
         pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Please Keep patience.Its loading...");

        pDialog.show();
        // changing action bar color
        //getActionBar().setBackgroundDrawable(
              //  new ColorDrawable(Color.parseColor("#1b1b1b")));
        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                //movie.setTitle(obj.getString("title"));
                                movie.setName(obj.getString("name"));
                                //movie.setThumbnailUrl(obj.getString("image"));
                                movie.setThumbnailUrl(obj.getString("org_image_url"));
                                movie.setAverage_ratings(obj.getString("average_ratings"));
                                movie.setCuisine(obj.getString("cuisine"));
                                movie.setAddress(obj.getJSONObject("address").getString("area"));
                               // movie.setAddress(obj.getJSONObject("address").getString("full_address"));
                                movie.setCost(obj.getString("cost"));
                                //movie.setYear(obj.getInt("releaseYear"));
                                // Genre is json array
								/*JSONArray genreArry = obj.getJSONArray("genre");
								ArrayList<String> genre = new ArrayList<String>();
								for (int j = 0; j < genreArry.length(); j++) {
									genre.add((String) genreArry.get(j));
								}
								movie.setGenre(genre);*/
                                // adding movie to movies array
                                movieList.add(movie);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }



}




