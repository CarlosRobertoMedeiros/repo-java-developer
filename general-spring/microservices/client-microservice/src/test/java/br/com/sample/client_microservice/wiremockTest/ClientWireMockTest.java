package br.com.sample.client_microservice.wiremockTest;

import br.com.sample.client_microservice.BaseIntegrationTest;

public class ClientWireMockTest extends BaseIntegrationTest {

    @Test
    void deveSimularServicoExterno() {

        stubFor(get("/score/123")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("700")));

        // chamada simulada aqui
    }
}
