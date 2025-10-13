package br.com.roberto.br.samplehexagonal.ms_products.domain.ports.repositories;

import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Product;

import java.util.List;

public interface ProductRepositoryPort {
    List<Product> findAll();
    Product findBySku(String skyu);
    Product save(Product product);
}
