package com.app.movie.interfaces;

import com.app.movie.entities.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MovieInterface extends CrudRepository<Movie, String> {

    @Query(value= "{name : ?0}")
    List<Movie> getMoviesByName(String name);

}
