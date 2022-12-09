package com.app.movie.interfaces;

import com.app.movie.entities.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreInterface extends CrudRepository<Genre, String> {
    
}
