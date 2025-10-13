package br.com.roberto.br.samplehexagonal.ms_products.application.adapter.request;

import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
                             @NotBlank(message = "SKU is Required")
                             String sku,

                             @NotBlank(message = "Name is Required")
                             String name,

                             @NotNull(message = "Price is Required")
                             @Positive
                             BigDecimal price,

                             @NotNull(message = "Quantity not be null")
                             @PositiveOrZero(message = "Quantity cannot be Negative")
                             Double quantity) {
    public Product toDomain() {
        return new Product(
                UUID.randomUUID(),
                sku,
                name,
                price.stripTrailingZeros(),
                quantity
        );
    }
}
