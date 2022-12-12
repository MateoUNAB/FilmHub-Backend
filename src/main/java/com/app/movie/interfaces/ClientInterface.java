package com.app.movie.interfaces;

import com.app.movie.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ClientInterface extends CrudRepository<Client, String> {

    @Query(value= "{email : ?0}")
    List<Client> getClientsByEmail(String email);

}
