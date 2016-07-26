package com.comida.adater;
import com.comida.R;
import com.comida.app.AppController;
import com.comida.model.Movie;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;


public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Movie> movieItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Movie> movieItems) {
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
		/*ImageView img;
		img = (ImageView)convertView
				.findViewById(R.id.img);

		img.setImageResource(R.drawable.bc);
		else {*/

		NetworkImageView _ImageView = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
		 _ImageView.setDefaultImageResId(R.drawable.bc);
		//NetworkImageView.setImageUrl(m.getThumbnailUrl(), ImageLoader);
		/*NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);*/
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView average_ratings = (TextView) convertView.findViewById(R.id.average_ratings);
		TextView address=(TextView) convertView.findViewById(R.id.area);
		TextView cuisine =(TextView) convertView.findViewById(R.id.cuisine);
		TextView cost =(TextView)  convertView.findViewById(R.id.cost);

		//TextView genre = (TextView) convertView.findViewById(R.id.genre);
		//TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

		// getting movie data for the row
		Movie m = movieItems.get(position);

		// thumbnail image
		//_ImageView.setImageUrl(m.getThumbnailUrl(), imageLoader);
		/*if (TextUtils.isEmpty(m.getThumbnailUrl()))
			thumbNail.setImageResource(R.drawable.bc);
	else
			//Log.d("KeyHash:","Neeraj");*/
		_ImageView.setImageUrl(m.getThumbnailUrl(), imageLoader);
		/*if (m.getThumbnailUrl().compareTo("")!=0)
			thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		//else{
		//thumbNail.setImageResource(R.drawable.bc);

			else {

				thumbNail.setDefaultImageResId(R.drawable.bc);
				//thumbNail.setErrorImageResId(R.drawable.bc);

		}*/


		// title
		name.setText(m.getName());

		// rating
		average_ratings.setText("Rating: " + String.valueOf(m.getAverage_ratings()));
		address.setText("Area: " + String.valueOf(m.getAddress()));
		cuisine.setText("Cusine: " + String.valueOf(m.getCuisine()));
		cost.setText("Cost for Two: " + "\t"+"₹"+ String.valueOf(m.getCost()));
		/*// genre
		String genreStr = "";
		for (String str : m.getGenre()) {
			genreStr += str + ", ";
		}
		genreStr = genreStr.length() > 0 ? genreStr.substring(0,
				genreStr.length() - 2) : genreStr;
		genre.setText(genreStr);

		// release year
		year.setText(String.valueOf(m.getYear()));*/

		return convertView;
	}
}