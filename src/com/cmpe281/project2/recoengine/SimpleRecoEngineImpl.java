package com.cmpe281.project2.recoengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cmpe281.project2.db.AwsRdsDBImpl;
import com.cmpe281.project2.db.RecEngineDBLayerInterface;
import com.cmpe281.project2.entities.Movie;

public class SimpleRecoEngineImpl implements RecoEngineInterface {

	RecEngineDBLayerInterface db = AwsRdsDBImpl.getInstance();
	
	private static final int NO_OF_RECS = 5;
	private static final double MIN_RATING = 2.5;
	private static final int MIN_REVIEWS = 2000;
	
	@Override
	public List<Movie> getRecommendations(Movie movie) {
		int balanceRecs = NO_OF_RECS;
		List<Movie> mList = new ArrayList<Movie>();
		while(balanceRecs > 0){
			List<Movie> tempList = db.getTopMovies(movie.getGenres(), MIN_RATING, MIN_REVIEWS, balanceRecs, movie.getTitle());
			mList.addAll(tempList);
			balanceRecs = balanceRecs - tempList.size();
			if(balanceRecs > 0){
				truncateGenres(movie);
				if(movie.getGenres().equalsIgnoreCase("")){
					break;
				}
			}
		}
		return mList;
	}
	

	private void truncateGenres(Movie movie) {
		String [] genreList = movie.getGenres().split("\\|");
		StringBuilder temp = new StringBuilder();
		
		for(int i = 0; i < genreList.length - 1; i++){
			if(i == genreList.length - 2){
				temp.append(genreList[i]+"\r");
			}else{
				temp.append(genreList[i]+"|");
			}
		}
		
		movie.setGenres(temp.toString());
	}

	@Override
	public List<Movie> getRecommendations(String title, Double minRating, int noOfRatings) {
		return null;
	}

	@Override
	public List<Movie> getRecommendationsByGenre(String[] genreList, Double minRating, int noOfRatings) {
		List<Movie> mList = new ArrayList<Movie>();
		List<String> genres = Arrays.asList(genreList);
		List<Movie> tempList = db.getMoviesByGenres(genres, minRating, noOfRatings, 5);
		mList.addAll(tempList);
		return mList;
	}

	@Override
	public List<Movie> getRecommendations(Movie movie, Double minRating, int noOfRatings) {
		
		List<Movie> mList = new ArrayList<Movie>();
		int balanceRecs = NO_OF_RECS;
		while(balanceRecs > 0){
			List<Movie> tempList = db.getTopMovies(movie.getGenres(), minRating, noOfRatings, balanceRecs, movie.getTitle());
			mList.addAll(tempList);
			balanceRecs = balanceRecs - tempList.size();
			if(balanceRecs > 0){
				truncateGenres(movie);
				if(movie.getGenres().equalsIgnoreCase("")){
					break;
				}
			}
		}
		return mList;
	}
	
}
