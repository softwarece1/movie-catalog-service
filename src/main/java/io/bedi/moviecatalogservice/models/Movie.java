package io.bedi.moviecatalogservice.models;

public class Movie {
	private String movieId;
	private String name;
	
	public Movie() {}
	
	public Movie(String movieId, String movie) {
		this.movieId = movieId;
		this.name = movie;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String movie) {
		this.name = movie;
	}
	
	
}
