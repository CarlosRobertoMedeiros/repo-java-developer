package br.com.roberto.br.samplehexagonal.ms_products.infraestructure.adapters.entity;

import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
@Table(name = "TBL_Products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String sku;
    private String name;
    private BigDecimal price;
    private Double quantity;

    public ProductEntity(){}

    public ProductEntity(Product product) {
        this.sku = product.sku();
        this.name = product.name();
        this.price = product.price().setScale(2, RoundingMode.HALF_UP);
        this.quantity = product.quantity();
    }

    public void update(Product product) {
        this.sku = product.sku();
        this.name = product.name();
        this.price = product.price().setScale(2, RoundingMode.HALF_UP);
        this.quantity = product.quantity();
    }

    public Product toProduct() {
        return new Product(id, sku, name, price, quantity);
    }
}
