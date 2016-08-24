package com.comida.adater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.comida.R;
import com.comida.app.AppController;
import com.comida.model.MovieCuisine;

import java.util.List;

/**
 * Created by techaboard user on 24/08/2016.
 */
public class CustomListAdapterCuisine extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MovieCuisine> movieItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapterCuisine(Activity activity, List<MovieCuisine> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();


        NetworkImageView _ImageView = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        _ImageView.setDefaultImageResId(R.drawable.bc);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView average_ratings = (TextView) convertView.findViewById(R.id.average_ratings);
        TextView address=(TextView) convertView.findViewById(R.id.area);
        TextView cuisine =(TextView) convertView.findViewById(R.id.cuisine);
        TextView cost =(TextView)  convertView.findViewById(R.id.cost);
//        TextView distance=(TextView) convertView.findViewById(R.id.Distance);
        TextView text=(TextView) convertView.findViewById(R.id.text1);

        // getting movie data for the row
        MovieCuisine m = movieItems.get(position);


        _ImageView.setImageUrl(m.getThumbnailUrl(), imageLoader);



        // title
        name.setText(m.getName());

        // rating
        average_ratings.setText("Rating: " + String.valueOf(m.getAverage_ratings()));
        address.setText("Area: " + String.valueOf(m.getAddress()));
              cuisine.setText("Cusine: " + String.valueOf(m.getCuisine()));
        cost.setText("Cost for Two: " + "\t"+"₹"+ String.valueOf(m.getCost()));
        text.setText("Deal:"+ "\t"+String.valueOf(m.getText()));
        //      distance.setText("Distance in Km. "+Double.valueOf( m.getDistance()));


        return convertView;
    }
}