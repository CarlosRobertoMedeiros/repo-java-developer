package br.com.roberto.ms_conversor.infraestructure.adapter.input.request;

import br.com.roberto.ms_conversor.domain.model.ItemVenda;

import java.math.BigDecimal;
import java.util.Objects;

public record ItemRequest(String quantity, double unitAmount, String description) {

    public ItemRequest {
        Objects.requireNonNull(quantity, "A quantidade é obrigatória");
        Objects.requireNonNull(description, "A descrição é obrigatória");
    }

    public static ItemVenda toDomain(ItemRequest request) {
        return new ItemVenda(
                new BigDecimal(request.quantity()),
                BigDecimal.valueOf(request.unitAmount()),
                request.description()
        );
    }

    public static ItemRequest fromDomain(ItemVenda item) {
        return new ItemRequest(
                item.quantity().toPlainString(),
                item.unitAmount().doubleValue(),
                item.description()
        );
    }


}
