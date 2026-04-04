package br.com.sample.client_microservice.domain;

import br.com.sample.client_microservice.model.Client;
import br.com.sample.client_microservice.ports.inbound.ClientRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;


    public ClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    public Client execute(Client client){
        return clientRepositoryPort.salvar(client);
    }

}
