package com.app.movie.service;

import com.app.movie.dto.AuthDto;
import com.app.movie.dto.AuthResponseDto;
import com.app.movie.entities.Client;
import com.app.movie.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    ClientRepository repository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponseDto check(AuthDto request) {
        Optional<Client> client = checkCredential(request.user, request.password);
        AuthResponseDto response = new AuthResponseDto();
        if (client != null && client.isPresent()) {
            response.id = client.get().getId();
            response.name = client.get().getName() + " " + client.get().getLastName();
            response.email = client.get().getEmail();
            response.token = getToken(client.get());
        }
        return response;
    }

    public String getToken (Client client) {
        return "";
    }

    public Optional<Client> checkCredential(String user, String password) {

        Optional<Client> client = repository.getByEmail(user);
        if (!matchPass(password, client.get().getPassword())) {
            return null;
        }
        return client;
    }

    private Boolean matchPass(String pass, String dbPass) {
        return this.passwordEncoder.matches(pass, dbPass);
    }

}
