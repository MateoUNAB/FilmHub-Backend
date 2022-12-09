package com.app.movie.interfaces;

import com.app.movie.entities.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieInterface extends CrudRepository<Movie, String> {
    
}
