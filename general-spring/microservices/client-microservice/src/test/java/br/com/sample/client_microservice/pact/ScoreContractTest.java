package br.com.sample.client_microservice.pact;

@ExtendWith(PactConsumerTestExt.class)
public class ScoreContractTest {

    @Pact(provider = "score-service", consumer = "cliente-service")
    public RequestResponsePact pact(PactDslWithProvider builder) {

        return builder
                .uponReceiving("consulta score")
                .path("/score/123")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("700")
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pact")
    void validar(MockServer server) {

        var response = new RestTemplate()
                .getForObject(server.getUrl()+"/score/123", String.class);

        assertThat(response).isEqualTo("700");
    }


}
