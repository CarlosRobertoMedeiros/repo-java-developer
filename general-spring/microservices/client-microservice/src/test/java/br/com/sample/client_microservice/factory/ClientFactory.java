package br.com.sample.client_microservice.factory;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClientFactory {

    public static Map<String, Object> novo() {
        return Map.of(
                "nome", "Carlos " + System.currentTimeMillis(),
                "cpf", gerarCpf(),
                "idade", 43
        );
    }

    private static String gerarCpf() {
        return IntStream.range(0, 11)
                .mapToObj(i -> String.valueOf((int)(Math.random()*10)))
                .collect(Collectors.joining());
    }
}
