package com.parth.moviecatalogservice.controller;

import com.parth.moviecatalogservice.models.CatalogItem;
import com.parth.moviecatalogservice.models.Movie;
import com.parth.moviecatalogservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    private final RestTemplate restTemplate;

    public MovieCatalogController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratings/users/" + userId,
                UserRating.class);

        return ratings != null ? ratings.getUserRating().stream().map(rating -> {
            // for each movieId, call movie-info-service and get details
            Movie movie = restTemplate.getForObject(
                    "http://localhost:8082/movies/" + rating.getMovieId(), Movie.class
            );
            return new CatalogItem(movie != null ? movie.getName() : null, "Description", rating.getRating());
        }).toList() : null;
    }
}
