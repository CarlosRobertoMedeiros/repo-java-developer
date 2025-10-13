package br.com.roberto.br.samplehexagonal.ms_products.application.adapter.response;

import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id,
                              String sku,
                              String name,
                              BigDecimal price,
                              Double quantity) {
    public static ProductResponse fromDomain(Product product) {
        return new ProductResponse(
                 product.id(),
                product.sku(),
                product.name(),
                product.price() !=null ? product.price().stripTrailingZeros() : null, //BigDecimal Safe and Immutable
                product.quantity()
        );
    }

}
