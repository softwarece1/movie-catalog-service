package io.bedi.moviecatalogservice.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.bedi.moviecatalogservice.models.MovieList;
import io.bedi.moviecatalogservice.models.UserRating;

@FeignClient(name="Spring-Cloud-Api-Gateway")
public interface FeignProxyController {

	@RequestMapping("/ratingsdata/users/{user}")
	UserRating getRList(@PathVariable("user") String user);
	
	@RequestMapping("/movies/")
	MovieList getMDetails();
}
