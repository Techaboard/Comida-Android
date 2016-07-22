package com.comida;

/**
 * Created by techaboard user on 21/07/2016.
 */
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.comida.MainActivity;
import com.comida.R;
import com.comida.adater.CustomListAdapter;
import com.comida.app.AppController;
import com.comida.model.Movie;

public class ListViewActivity extends AppCompatActivity {
    // Log tag
    private static final String TAG = ListViewActivity.class.getSimpleName();
    // change here url of server api
    private static final String url = "http://02a53fbe.ngrok.io/api/v1/restaurants?per_page=10&page=1&sort_col=average_ratings";

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
        listView.setAdapter(adapter);
         pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
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
                                movie.setThumbnailUrl(obj.getString("image_url"));
                                movie.setAverage_ratings(obj.getString("average_ratings"));
                                movie.setCuisine(obj.getString("cuisine"));
                                movie.setAddress(obj.getJSONObject("address").getString("area"));
                                //movie.setAddress(obj.getString("address"));
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

}




