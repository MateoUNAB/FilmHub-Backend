package com.app.movie.service;

import com.app.movie.dto.ReportClientDto;
import com.app.movie.dto.ResponseDto;
import com.app.movie.entities.Client;
import com.app.movie.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final String CLIENT_REGISTERED="El cliente ya se encuentra registrado";
    private final String CLIENT_SUCCESS="El cliente se registró correctamente";

    @Autowired
    ClientRepository repository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Iterable<Client> get() {
        Iterable<Client> response = repository.getAll();
        return response;
    }

    public ReportClientDto getReport() {
        Optional<Client> client = repository.findById("6380442df71ad74770fc57e1");
        ReportClientDto reportClientDto= new ReportClientDto();
        reportClientDto.birthDate=client.get().getBirthDate();
        reportClientDto.email=client.get().getEmail();
        reportClientDto.id=client.get().getId();
        return reportClientDto;
    }

    public ResponseDto create(Client request) {
        request.setPassword(encrypt(request.getPassword()));
        ResponseDto response = new ResponseDto();
        List<Client> clients = repository.getListByEmail(request.getEmail());

        if (clients.size() > 0){
            response.status = false;
            response.message = CLIENT_REGISTERED;
        }
        else {
            repository.save(request);
            response.status = true;
            response.message = CLIENT_SUCCESS;
            response.id = request.getId();
        }
        return response;
    }

    public Client update(Client client) {
        Client clientToUpdate = new Client();

        Optional<Client> currentClient = repository.findById(client.getId());
        if (!currentClient.isEmpty()) {            
            clientToUpdate = client;
            clientToUpdate = repository.save(clientToUpdate);
        }
        return clientToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }

    private String encrypt(String pass) { return this.passwordEncoder.encode(pass);}

}
