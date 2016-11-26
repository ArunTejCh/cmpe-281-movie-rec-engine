package com.cmpe281.project2.entities;

public class Movie {

	private String title;
	private String genres;
	private double avgRating;
	private int noOfReviews;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public int getNoOfReviews() {
		return noOfReviews;
	}

	public void setNoOfReviews(int noOfReviews) {
		this.noOfReviews = noOfReviews;
	}

	public Movie(String title, String genres, double avgRating, int noOfReviews) {
		super();
		this.title = title;
		this.genres = genres;
		this.avgRating = avgRating;
		this.noOfReviews = noOfReviews;
	}

	public Movie() {
		super();
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", genres=" + genres + ", avgRating=" + avgRating + ", noOfReviews="
				+ noOfReviews + "]";
	}

}
