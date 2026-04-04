package br.com.sample.client_microservice.rest;

public class ClientClient {

    public static ValidatableResponse criar(Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/clientes")
                .then();
    }
}
