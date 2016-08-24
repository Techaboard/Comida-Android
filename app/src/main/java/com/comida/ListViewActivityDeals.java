package com.comida;

/**
 * Created by techaboard user on 22/08/2016.
 */


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.comida.adater.CustomListAdapterDeals;
import com.comida.app.AppController;
import com.comida.model.MovieDeals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivityDeals extends Activity {
    // Log tag
    private static final String TAG = ListViewActivityDeals.class.getSimpleName();
    // change here url of server api
    private static final String url = "https://comida-95724.herokuapp.com/api/v1/restaurants/get_restaurants_with_offers?sort_dir=asc";
    private ProgressDialog pDialog;
    private List<MovieDeals> movieList = new ArrayList<>();
    private ListView listView;
    private CustomListAdapterDeals adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_deals);
        listView = (ListView) findViewById(R.id.list_deals);
        adapter = new CustomListAdapterDeals(this, movieList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieDeals movie = movieList.get(position);
                Intent intent = new Intent(ListViewActivityDeals.this, SecondActivity.class);
                intent.putExtra("name", movie.getName());
                intent.putExtra("average_ratings", movie.getAverage_ratings());
                intent.putExtra("full_address", movie.getAddress());
                intent.putExtra("image_url", movie.getThumbnailUrl());
                //intent.putExtra("cuisine",movie.getCuisine());
                intent.putExtra("cost",movie.getCost());
                startActivity(intent);

            }
        });
        listView.setAdapter(adapter);
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Please Keep patience.Its loading...");

        pDialog.show();

        // Creating volley request obj
        JsonObjectRequest movieReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                JSONArray

                        restaurantsJSONArray= null;
                try {
                    restaurantsJSONArray = response.getJSONArray("restaurants");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                hidePDialog();
                // Parsing json
                for (int i = 0; i < restaurantsJSONArray.length(); i++) {
                    try {

                        JSONObject obj =restaurantsJSONArray.getJSONObject(i);
                        MovieDeals movie = new MovieDeals();
                        //movie.setTitle(obj.getString("title"));
                        movie.setName(obj.getString("name"));
                        //movie.setThumbnailUrl(obj.getString("image"));
                        movie.setThumbnailUrl(obj.getString("org_image_url"));
                        movie.setAverage_ratings(obj.getString("average_ratings"));
                      //  movie.setCuisine(obj.getString("cuisine"));
                        movie.setAddress(obj.getJSONObject("address").getString("area"));
                        // movie.setAddress(obj.getJSONObject("address").getString("full_address"));
                        movie.setCost(obj.getString("cost"));
//                        movie.setDistance( obj.getDouble("distance"));

                        JSONArray    textJSONArray= obj.getJSONArray("restaurant_offers");

                        for ( int j = 0; j < textJSONArray.length(); j++) {


                            JSONObject txtobj =textJSONArray.getJSONObject(j);
                            //obj.getJSONArray("restaurant_offers").getJSONObject(0).getSt‌​ring("text");
                            //movie.settext(obj.getJSONArray("restaurant_offers").getJSONObject(j).getSt‌​ring("text"));
                            movie.settext(txtobj .getString("text"));
                        }


                        // movie.settext(obj.getString("text"));
                        movieList.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });


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









