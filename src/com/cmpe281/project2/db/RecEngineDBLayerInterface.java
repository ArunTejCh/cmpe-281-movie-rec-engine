package com.cmpe281.project2.db;

import java.util.List;

import com.cmpe281.project2.entities.Movie;

public interface RecEngineDBLayerInterface {

	List<Movie> getTopMovies(String genres, Double rating, int noOfRatings, int count, String curTitle);
	
	List<Movie> getMoviesByTitle(String title);
	
	List<Movie> getMoviesByGenres(List<String> genres, Double rating, int noOfRatings, int count);
}
