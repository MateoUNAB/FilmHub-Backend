package com.app.movie.service;

import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Genre;
import com.app.movie.interfaces.GenreInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    GenreInterface repository;

    public Iterable<Genre> get() {
        Iterable<Genre> response = repository.findAll();
        return response;
    }

    public ResponseDto create(Genre request) {

        Genre newGenre = repository.save(request);

        ResponseDto responseDto = new ResponseDto();
        responseDto.status=true;
        responseDto.message="Genero creado correctamente";
        responseDto.id= newGenre.getId();
        return responseDto;

    }

    public Genre update(Genre genre) {
        Genre genreToUpdate = new Genre();
        if (repository.existsById(genre.getId())) {
            genreToUpdate = genre;
            repository.save(genreToUpdate);
        }
        return genreToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }

}
