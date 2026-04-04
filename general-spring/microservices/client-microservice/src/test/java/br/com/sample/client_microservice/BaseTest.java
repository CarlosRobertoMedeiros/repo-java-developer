package br.com.sample.client_microservice;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class BaseIntegrationTest extends PostgresContainerConfig {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }
}