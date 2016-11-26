package com.cmpe281.project2.recoengine;

import java.util.ArrayList;
import java.util.List;

import com.cmpe281.project2.db.AwsRdsDBImpl;
import com.cmpe281.project2.db.RecEngineDBLayerInterface;
import com.cmpe281.project2.entities.Movie;

public class SimpleRecoEngineImpl implements RecoEngineInterface {

	RecEngineDBLayerInterface db = AwsRdsDBImpl.getInstance();
	
	private static final int NO_OF_RECS = 5;
	private static final double MIN_RATING = 2.5;
	private static final int MIN_REVIEWS = 50;
	
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
		
		for(int i = 1; i < genreList.length; i++){
			if(i == genreList.length - 1){
				temp.append(genreList[i]);
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
	public List<Movie> getRecommendationsByGenre(String genreList, Double minRating, int noOfRatings) {
		return null;
	}

	
	
	
	
	
	
}
