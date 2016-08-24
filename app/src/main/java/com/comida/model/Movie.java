package com.comida.model;

public class Movie {
	private String name, thumbnailUrl;
	private String average_ratings;
	private String area;
	private String cuisine;
	private String address;
	private String cost;
	private String longitude;
	private String latitude;
	private Object distance ;
	private String text;
	public char[] gettext;

	public Movie() {
	}
	public Movie(String name, String thumbnailUrl, String average_ratings, String area, String cuisine, String address,String cost,String latitude,String longitude,double distance,String text
	) {
		this.name = name;
		this.thumbnailUrl = thumbnailUrl;
		//this.year = year;
		this.average_ratings = average_ratings;
		this.area=area;
		this.cuisine=cuisine;
		this.cost=cost;
		this.latitude=latitude;
		this.longitude=longitude;
		this.distance=distance;
		this.text=text;




		this.address=address;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}


	public String getAverage_ratings() {
		return average_ratings;
	}

	public void setAverage_ratings(String average_ratings) {
		this.average_ratings = average_ratings;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;

	}

	public String getlatitude() {
		return latitude;
	}
	public void setlatitude(String latitude ){this.latitude=latitude;}

	public String getlongitude() {
		return longitude;
	}
	public void setlongitude (String longitude ){this.longitude=longitude;}


	public double getDistance() {
		return (double) distance;
	}
	public void setDistance(double distance){this.distance=distance;}


	/*public String  getText() {
		return  text;
	}
	public void settext(String text){this.text=text;}*/
}
