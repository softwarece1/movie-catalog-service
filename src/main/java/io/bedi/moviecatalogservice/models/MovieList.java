package io.bedi.moviecatalogservice.models;

import java.util.List;

public class MovieList {
	private List<Movie> movieList;
	
	public MovieList() {
	}
	
	public MovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}


	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}
}
