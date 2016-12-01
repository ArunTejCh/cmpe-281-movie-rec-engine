package com.cmpe281.project2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cmpe281.project2.entities.Movie;

public class AwsRdsDBImpl implements RecEngineDBLayerInterface {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://cmpe281-reco-engine.cyugrlujrowq.us-west-2.rds.amazonaws.com:3306/recoeng";

	// Database credentials
	static final String USER = "coder";
	static final String PASS = "coder1911";
	static final String DB_NAME = "recoeng";

	Connection conn = null;

	private static AwsRdsDBImpl instance = null;

	public static AwsRdsDBImpl getInstance() {
		if (instance == null) {
			instance = new AwsRdsDBImpl();
		}
		return instance;
	}

	private AwsRdsDBImpl() {
		openConnection();
	}

	private void openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to aws database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Movie> getTopMovies(String genres, Double rating, int noOfRatings, int count, String curTitle) {
		List<Movie> movieList = null;
		try {
			if (conn.isClosed()) {
				openConnection();
			}
			String sql;
			sql = "select * from movies where avg_rating > ? and no_of_ratings > ? and genres = ? and title != ? ORDER BY avg_rating DESC limit ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setDouble(1, rating);
			pst.setInt(2, noOfRatings);
			pst.setString(3, genres);
			pst.setString(4, curTitle);
			pst.setInt(5, count);
			ResultSet rs = pst.executeQuery();
			movieList = new ArrayList<Movie>();
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("title");
				String curGenres = rs.getString("genres");
				int no = rs.getInt("no_of_ratings");
				double curRating = rs.getDouble("avg_rating");
				movieList.add(new Movie(name, curGenres, Math.round(curRating * 10D) / 10D, no));
			}
			rs.close();
			pst.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return movieList;
	}

	@Override
	public List<Movie> getMoviesByTitle(String title) {
		List<Movie> movieList = null;
		try {
			if (conn.isClosed()) {
				openConnection();
			}
			String sql;
			sql = "SELECT * from movies where title like ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			String searchTitle = "%" + title + "%";
			pst.setString(1, searchTitle);
			ResultSet rs = pst.executeQuery();
			movieList = new ArrayList<Movie>();
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("title");
				String genres = rs.getString("genres");
				int no = rs.getInt("no_of_ratings");
				double rating = rs.getDouble("avg_rating");
				movieList.add(new Movie(name, genres, Math.round(rating * 10D) / 10D, no));
			}
			rs.close();
			pst.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return movieList;
	}

	@Override
	public List<Movie> getMoviesByGenres(List<String> genres, Double rating, int noOfRatings, int count) {
		List<Movie> movieList = null;
		try {
			if (conn.isClosed()) {
				openConnection();
			}
			List<String> likeString = new ArrayList<>();
			for(String genre :genres){
				StringBuilder queryBuiler = new StringBuilder();
				queryBuiler.append("genres LIKE '%"+genre+"%' ");
				queryBuiler.append(" AND ");
				likeString.add(queryBuiler.toString());
			}
			int numberOfLike = likeString.size();
			String lastLike = likeString.get(numberOfLike-1);
			StringBuilder queryBuiler = new StringBuilder(lastLike);
			int queryLength = queryBuiler.length();
			queryBuiler.delete(queryLength-5, queryLength-1);
			likeString.remove(numberOfLike-1);
			likeString.add(numberOfLike-1, queryBuiler.toString());
			
			StringBuilder finalQuery = new StringBuilder();
			for(String query:likeString){
				finalQuery.append(query);
			}
			
			String sql1,sql2;
			sql1 = "select * from movies where avg_rating > ? and no_of_ratings > ? and ";
			sql2= "ORDER BY avg_rating DESC limit ?";
			PreparedStatement pst = conn.prepareStatement(sql1+finalQuery.toString()+sql2);
			pst.setDouble(1, rating);
			pst.setInt(2, noOfRatings);
			pst.setInt(3, count);
			ResultSet rs = pst.executeQuery();
			movieList = new ArrayList<Movie>();
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("title");
				String curGenres = rs.getString("genres");
				int no = rs.getInt("no_of_ratings");
				double curRating = rs.getDouble("avg_rating");
				movieList.add(new Movie(name, curGenres, Math.round(curRating * 10D) / 10D, no));
			}
			rs.close();
			pst.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return movieList;
	}

}
