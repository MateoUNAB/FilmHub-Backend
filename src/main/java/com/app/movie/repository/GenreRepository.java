package com.app.movie.repository;

import com.app.movie.entities.Genre;
import com.app.movie.interfaces.GenreInterface;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepository {
    
    @Autowired
    GenreInterface repository;
    
    public Iterable<Genre> getAll(){
        return repository.findAll();
    }
    
    public Optional<Genre> findById(String id){
        Optional<Genre> response= repository.findById(id);
        return response;
    }
    
    public Boolean existsById(String id){
        return repository.existsById(id);
    }
    
    public void deleteById(String id){
        repository.deleteById(id);
    }
    
    public Genre save(Genre genre){
        return repository.save(genre);
    }
    
}
