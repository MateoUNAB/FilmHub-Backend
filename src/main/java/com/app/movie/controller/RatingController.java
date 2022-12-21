package com.app.movie.controller;

import com.app.movie.dto.RatingDto;
import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Rating;
import com.app.movie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
@CrossOrigin(origins = "*")
public class RatingController {

    @Autowired
    RatingService service;

    @GetMapping("")
    public Iterable<Rating> get() {
        return service.get();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto create(@RequestBody RatingDto request) {
        return service.create(request);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Rating update(@RequestBody Rating request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}
