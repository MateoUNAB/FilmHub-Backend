package com.app.movie.interfaces;

import com.app.movie.entities.Movie;
import com.app.movie.entities.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingInterface extends CrudRepository<Rating, String> {

    @Query(value= "{movie.id : ?0}, client.id : ?1}")
    List<Movie> getRatingByMovieAndClient(String movieId, String clientId);

}
