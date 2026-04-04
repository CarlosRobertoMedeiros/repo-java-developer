package br.com.sample.client_microservice.infraestructure.controller;

import br.com.sample.client_microservice.domain.ClientUseCase;
import br.com.sample.client_microservice.model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientUseCase clientUseCase;

    public ClientController(ClientUseCase clientUseCase){
        this.clientUseCase = clientUseCase;
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client){
        return ResponseEntity.status(201)
                .body(clientUseCase.execute(client));
    }



}
