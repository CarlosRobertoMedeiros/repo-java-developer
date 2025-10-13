package br.com.roberto.br.samplehexagonal.ms_products.domain.adapter.service;

import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Inventory;
import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Product;
import br.com.roberto.br.samplehexagonal.ms_products.domain.exceptions.ProductNotFoundException;
import br.com.roberto.br.samplehexagonal.ms_products.domain.ports.interfaces.ProductServicePort;
import br.com.roberto.br.samplehexagonal.ms_products.domain.ports.repositories.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderImp implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;

    public OrderImp(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public List<Product> findProducts() {
        return this.productRepositoryPort.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return this.productRepositoryPort.save(product);
    }

    @Override
    public void updateInventory(String sku, Inventory inventory) {
        Product product = this.productRepositoryPort.findBySku(sku);

        if (Objects.isNull(product))
            throw new ProductNotFoundException(String.format("Product %s Not Found !",sku));

        product = product.updateInventory(inventory.quantity());
        this.productRepositoryPort.save(product);
    }
}
