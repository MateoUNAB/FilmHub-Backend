package com.app.movie.service;

import com.app.movie.dto.RatingDto;
import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Client;
import com.app.movie.entities.Movie;
import com.app.movie.entities.Rating;
import com.app.movie.repository.ClientRepository;
import com.app.movie.repository.MovieRepository;
import com.app.movie.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository repository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ClientRepository clientRepository;

    public Iterable<Rating> get() {
        Iterable<Rating> response = repository.getAll();
        return response;
    }

    public ResponseDto create(RatingDto request) {
        ResponseDto response = new ResponseDto();

        if (request.rating < 0 || request.rating > 10){
            response.status = false;
            response.message = "La calificacion no esta dentro de los valores validos";
        }
        else {
            Rating rating = new Rating();

            Optional<Movie> movie = movieRepository.findById(request.movieId);
            Optional<Client> client = clientRepository.findById(request.clientId);

            if (movie.isPresent() && client.isPresent()){

                rating.setStatus("active");
                rating.setRating(request.rating);
                rating.setMovie(movie.get());
                rating.setClient(client.get());
                repository.save(rating);
                response.status = true;
                response.message = "Calificacion guardada correctamente";
                response.id = rating.getId();
            }

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
