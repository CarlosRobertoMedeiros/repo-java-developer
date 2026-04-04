package br.com.sample.client_microservice;

import br.com.sample.client_microservice.infraestructure.persistence.ClientEntity;
import br.com.sample.client_microservice.infraestructure.persistence.ClientJpaRepository;
import br.com.sample.client_microservice.model.Client;
import br.com.sample.client_microservice.ports.inbound.ClientRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientJpaRepository clientJpaRepository;

    public ClientRepositoryAdapter(ClientJpaRepository clientJpaRepository){
        this.clientJpaRepository = clientJpaRepository;
    }

    @Override
    public Client salvar(Client cliente) {
        var entity = new ClientEntity();
        entity.setName(cliente.nome());
        entity.setCpf(cliente.cpf());
        entity.setIdade(cliente.idade());

        var saved = clientJpaRepository.save(entity);
        return new Client(saved.getName(), saved.getCpf(), saved.getIdade());
    }

    @Override
    public Optional<Client> buscarPorCpf(String cpf) {
        return clientJpaRepository.findByCpf(cpf)
                .map(e -> new Client(e.getName(), e.getCpf(),  e.getIdade()));
    }
}
