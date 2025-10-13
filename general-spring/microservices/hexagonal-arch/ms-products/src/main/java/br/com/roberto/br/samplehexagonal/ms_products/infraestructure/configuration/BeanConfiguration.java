package br.com.roberto.br.samplehexagonal.ms_products.infraestructure.configuration;

import br.com.roberto.br.samplehexagonal.ms_products.domain.adapter.service.OrderImp;
import br.com.roberto.br.samplehexagonal.ms_products.domain.ports.interfaces.ProductServicePort;
import br.com.roberto.br.samplehexagonal.ms_products.domain.ports.repositories.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductServicePort productServicePort(ProductRepositoryPort productRepositoryPort){
        return new OrderImp(productRepositoryPort);
    }
}
