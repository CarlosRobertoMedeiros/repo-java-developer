package br.com.roberto.ms_conversor.infraestructure.adapter.input.request;

import br.com.roberto.ms_conversor.domain.model.ItemVenda;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record BillingRequest(List<ItemRequest> items) {

    public BillingRequest {
        Objects.requireNonNull(items, "A lista de itens não pode ser nula");
    }

    public static List<ItemVenda> toDomain(BillingRequest billingRequest) {
        return billingRequest.items().stream()
                .map(ItemRequest::toDomain)
                .collect(Collectors.toList());
    }

    public static BillingRequest fromDomain(List<ItemVenda> domainItems) {
        List<ItemRequest> dtoItems = domainItems.stream()
                .map(ItemRequest::fromDomain)
                .toList();
        return new BillingRequest(dtoItems);
    }

}
