//package io.bedi.moviecatalogservice.resource;
//
//import java.lang.reflect.ParameterizedType;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClient.Builder;
//
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//
//import io.bedi.moviecatalogservice.models.CatalogItem;
//import io.bedi.moviecatalogservice.models.Movie;
//import io.bedi.moviecatalogservice.models.UserRating;
//
//@RestController
//@RequestMapping("/catalog")
//public class MovieCatalogResource {
//	
////	@Autowired
////	private WebClient.Builder webClientBuilder;
//	
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@RequestMapping("/{userId}")
//	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
//	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
//		  		
//		//get all movie ids
//		UserRating ratings = restTemplate.getForObject("http://Spring-Cloud-Api-Gateway/ratingsdata/users/"+userId, UserRating.class);
//
//		
//		return ratings.getUserRating().stream().map( rating -> {
//			//for each movie id, call movie info service and get details
//			Movie movie = restTemplate.getForObject("http://Spring-Cloud-Api-Gateway/movies/" +rating.getMovieId(), Movie.class);
//			//put them all together
//			return new CatalogItem(movie.getName(), "Test", rating.getRating());
//		}).collect(Collectors.toList());
//		
//	}
//	
//	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
//		return Arrays.asList(new CatalogItem("No movie","",0)); 
//	}
//}
//
///*
////asynchronous
//Movie movie = webClientBuilder.build()
//.get()
//.uri("http://localhost:8082/movies/" +rating.getMovieId())
//.retrieve()
//.bodyToMono(Movie.class)
//.block();
//*/