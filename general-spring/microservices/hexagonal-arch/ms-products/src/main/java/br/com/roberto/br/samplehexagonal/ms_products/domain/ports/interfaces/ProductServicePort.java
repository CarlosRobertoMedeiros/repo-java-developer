package br.com.roberto.br.samplehexagonal.ms_products.domain.ports.interfaces;

import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Inventory;
import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Product;

import java.util.List;

public interface ProductServicePort {
    List<Product> findProducts();
    Product createProduct(Product product);
    void updateInventory(String sku, Inventory inventory);
}
