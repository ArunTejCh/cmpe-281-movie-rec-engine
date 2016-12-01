package com.cmpe281.project2.recoengine;

import java.util.List;

import com.cmpe281.project2.entities.Movie;

public interface RecoEngineInterface {

	/**
	 * Base method for getting recommendations. Only takes title name as input. Returns a list of movies
	 * 
	 * @param title
	 * @return
	 */
	List<Movie> getRecommendations(Movie movie);
	
	/**
	 * Advanced method for getting recommendations. Provides more flexibility to user.
	 * 
	 * @param title
	 * @param minRating
	 * @param noOfRatings
	 * @return
	 */
	List<Movie> getRecommendations(String title, Double minRating, int noOfRatings);
	
	/**
	 * Fetches movies recommendations according to a given combination of genres, rating, no of reviews
	 * 
	 * @param genreList
	 * @param minRating
	 * @param noOfRatings
	 * @return
	 */
	List<Movie> getRecommendationsByGenre(String[] genreList, Double minRating, int noOfRatings);
	
	List<Movie> getRecommendations(Movie movie, Double minRating, int noOfRatings);


}
