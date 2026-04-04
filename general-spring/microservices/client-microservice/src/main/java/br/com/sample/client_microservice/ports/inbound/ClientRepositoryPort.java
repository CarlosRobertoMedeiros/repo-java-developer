package br.com.sample.client_microservice.ports.inbound;

import br.com.sample.client_microservice.model.Client;

import java.util.Optional;

public interface ClientRepositoryPort {
    Client salvar(Client cliente);
    Optional<Client> buscarPorCpf(String cpf);
}
