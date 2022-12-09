package com.app.movie.interfaces;

import com.app.movie.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientInterface extends CrudRepository<Client, String> {
    
}
