package com.app.movie.repository;

import com.app.movie.entities.Rating;
import com.app.movie.interfaces.RatingInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RatingRepository {
    
    @Autowired
    RatingInterface repository;
    
    public Iterable<Rating> getAll(){
        return repository.findAll();
    }
    
    public Optional<Rating> findById(String id){
        Optional<Rating> response= repository.findById(id);
        return response;
    }
    
    public Boolean existsById(String id){
        return repository.existsById(id);
    }
    
    public void deleteById(String id){
        repository.deleteById(id);
    }
    
    public Rating save(Rating rating){
        return repository.save(rating);
    }
    
}
