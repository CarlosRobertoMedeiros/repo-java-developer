package br.com.roberto.br.samplehexagonal.ms_products.application.adapter.request;

import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Inventory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record InventoryRequest(
        @NotNull(message = "Quantity is required")
        @PositiveOrZero(message = "Quantity cannot be negative")
        Double quantity){

    public Inventory toDomain() {
        return new Inventory(
                quantity
        );
    }

}




