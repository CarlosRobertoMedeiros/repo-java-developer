package br.com.sample.client_microservice.integrationtest;

import br.com.sample.client_microservice.BaseIntegrationTest;
import br.com.sample.client_microservice.infraestructure.persistence.ClientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientIntegrationTest extends BaseIntegrationTest {

    @Autowired
    ClientJpaRepository repository;

    @Test
    void deveCriarClienteEPersistir() {

        var payload = ClienteFactory.novo();

        var response = ClienteClient.criar(payload)
                .statusCode(201)
                .extract();

        String cpf = response.jsonPath().getString("cpf");

        var clienteDb = repository.findByCpf(cpf);

        assertThat(clienteDb).isPresent();
    }

}
