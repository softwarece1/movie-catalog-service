package io.bedi.moviecatalogservice.resource;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import io.bedi.moviecatalogservice.models.CatalogItem;
import io.bedi.moviecatalogservice.models.Movie;
import io.bedi.moviecatalogservice.models.MovieList;
import io.bedi.moviecatalogservice.models.UserRating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
private static final String M_CATALOG_RESOURCE = "catalogService";
//	@Autowired
//	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private FeignProxyController ffFetcher;
		
	@CircuitBreaker(name = M_CATALOG_RESOURCE, fallbackMethod = "getFallbackCatalog")
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		  		
		//using rest template 1 and 2
		//1. get all movie idS and ratings
//		UserRating ratings = restTemplate.getForObject("http://Spring-Cloud-Api-Gateway/ratingsdata/users/"+userId, UserRating.class);

		//2. get list of all movies
//		MovieList movieList = restTemplate.getForObject("http://Spring-Cloud-Api-Gateway/movies/", MovieList.class);
		
		//using feignclient 3 and 4
		//3. get all movie idS and ratings
		UserRating ratings = ffFetcher.getRList(userId);
		
		//4. get list of all movies
		MovieList movieList = ffFetcher.getMDetails();
		
		return ratings.getUserRating().stream().map( rating -> {
			//for each movie id, call movie info service and get details
			
			//call movie info service and get details of the each of the movies
			List<Movie> mList = movieList.getMovieList()
								.stream()
								.filter(item -> item.getMovieId().equals(rating.getMovieId()))
								.collect(Collectors.toList());
			
			Movie movie = mList.get(0); 

			return new CatalogItem(movie.getMovieId(), movie.getName(), rating.getRating());
		}).collect(Collectors.toList());
		
	}
	
	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId, Exception e) {
		return Arrays.asList(new CatalogItem("This is Fallback Method","None",0)); 
	}
}

/*
//asynchronous
Movie movie = webClientBuilder.build()
.get()
.uri("http://localhost:8082/movies/" +rating.getMovieId())
.retrieve()
.bodyToMono(Movie.class)
.block();
*/