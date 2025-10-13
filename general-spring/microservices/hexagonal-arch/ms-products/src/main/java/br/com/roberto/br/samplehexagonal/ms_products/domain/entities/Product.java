package br.com.roberto.br.samplehexagonal.ms_products.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(
        UUID id,
        String sku,
        String name,
        BigDecimal price,
        Double quantity
) {
    public Product updateInventory(double newQuantity) {
        return new Product(this.id(), this.sku(), this.name(), this.price(), newQuantity);
    }
}
