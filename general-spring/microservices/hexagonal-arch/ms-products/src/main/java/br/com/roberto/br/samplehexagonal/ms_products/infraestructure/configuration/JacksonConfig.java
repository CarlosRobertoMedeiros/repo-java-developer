package br.com.roberto.br.samplehexagonal.ms_products.infraestructure.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper()
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
    }
}
