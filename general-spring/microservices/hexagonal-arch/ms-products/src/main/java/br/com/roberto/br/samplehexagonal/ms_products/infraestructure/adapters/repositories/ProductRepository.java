package br.com.roberto.br.samplehexagonal.ms_products.infraestructure.adapters.repositories;

import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Product;
import br.com.roberto.br.samplehexagonal.ms_products.domain.ports.repositories.ProductRepositoryPort;
import br.com.roberto.br.samplehexagonal.ms_products.infraestructure.adapters.entity.ProductEntity;
import br.com.roberto.br.samplehexagonal.ms_products.infraestructure.adapters.exceptions.ProductDataBaseNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepository implements ProductRepositoryPort {

    private final SpringProductRepository springProductRepository;

    public ProductRepository(SpringProductRepository springProductRepository) {
        this.springProductRepository = springProductRepository;
    }


    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntities = this.springProductRepository.findAll();
        return productEntities.stream()
                .map(ProductEntity::toProduct)
                .collect(Collectors.toList());

    }

    @Override
    public Product findBySku(String sku) {
        Optional<ProductEntity> productEntity = this.springProductRepository.findBySku(sku);

        if (productEntity.isEmpty()){
            throw new ProductDataBaseNotFoundException(String.format("Product Not Found Exception With Sku %s",sku));
        }

        return productEntity.get().toProduct();
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity;

        if (product.id() != null) {
            productEntity = springProductRepository.findById(product.id())
                    .map(entity -> {
                        entity.update(product);
                        return entity;
                    })
                    .orElseGet(() -> new ProductEntity(product));
        } else {
            productEntity = new ProductEntity(product);
        }

        return springProductRepository.save(productEntity).toProduct();
    }
}
