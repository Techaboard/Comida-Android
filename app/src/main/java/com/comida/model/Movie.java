package com.comida.model;

public class Movie {
	private String name, thumbnailUrl;
	//private int year;
	private String average_ratings,area,cuisine,address;
//	private ArrayList<String> genre;

	public Movie() {
	}

	public Movie(String name, String thumbnailUrl, String average_ratings, String area, String cuisine, String address
			) {
		this.name = name;
		this.thumbnailUrl = thumbnailUrl;
		//this.year = year;
		this.average_ratings = average_ratings;
		this.area=area;
		this.cuisine=cuisine;
this.address=address;
		//this.genre = genre;
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

	/*public int getYear() {
		return year;
	}*/

	/*public void setYear(int year) {
		this.year = year;
	}*/

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
	/*public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}
*/
}
