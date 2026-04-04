package br.com.roberto.ms_conversor.infraestructure.adapter.input.request;

import java.time.LocalDateTime;
import java.util.Objects;

public record JsonOrigemRequest(
        LocalDateTime date,
        String type,
        String origin,
        String status,
        BillingRequest billingRequest
) {

    public JsonOrigemRequest {
        Objects.requireNonNull(date, "A data é obrigatória");
        Objects.requireNonNull(type, "O tipo é obrigatório");
        Objects.requireNonNull(origin, "A origem é obrigatória");
        Objects.requireNonNull(status, "O status é obrigatório");
        Objects.requireNonNull(billingRequest, "O billingRequest é obrigatório");
    }

    public static Venda toDomain(JsonOrigemRequest request) {
        List<ItemVenda> domainItems = BillingRequestConverter.toDomain(request.billingRequest());
        return new Venda(
                request.date(),
                request.type(),
                request.origin(),
                request.status(),
                domainItems
        );
    }

}
