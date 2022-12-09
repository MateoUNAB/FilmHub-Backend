package com.app.movie.interfaces;

import com.app.movie.entities.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingInterface extends CrudRepository<Rating, String> {
    
}
