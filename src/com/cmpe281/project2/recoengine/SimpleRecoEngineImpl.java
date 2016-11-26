package com.cmpe281.project2.recoengine;

import java.util.List;

import com.cmpe281.project2.db.AwsRdsDBImpl;
import com.cmpe281.project2.db.RecEngineDBLayerInterface;
import com.cmpe281.project2.entities.Movie;

public class SimpleRecoEngineImpl implements RecoEngineInterface {

	RecEngineDBLayerInterface db = AwsRdsDBImpl.getInstance();
	
	@Override
	public List<Movie> getRecommendations(String title) {
		return null;
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
