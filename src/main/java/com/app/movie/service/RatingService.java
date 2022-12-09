package com.app.movie.service;

import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Movie;
import com.app.movie.entities.Rating;
import com.app.movie.repository.MovieRepository;
import com.app.movie.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository repository;

    public Iterable<Rating> get() {
        Iterable<Rating> response = repository.getAll();
        return response;
    }

    public ResponseDto create(Rating request) {
        ResponseDto response = new ResponseDto();

        if (request.getRating() < 0 || request.getRating() > 10){
            response.status = false;
            response.message = "La calificacion no esta dentro de los valores validos";
        }
        else {
            repository.save(request);
            response.status = true;
            response.message = "Calificacion guardada correctamente";
            response.id = request.getId();
        }

        return response;

    }

    public Rating update(Rating rating) {
        Rating ratingToUpdate = new Rating();

        Optional<Rating> currentRating = repository.findById(rating.getId());
        if (!currentRating.isEmpty()) {
            ratingToUpdate = rating;
            ratingToUpdate = repository.save(ratingToUpdate);
        }
        return ratingToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }

}
