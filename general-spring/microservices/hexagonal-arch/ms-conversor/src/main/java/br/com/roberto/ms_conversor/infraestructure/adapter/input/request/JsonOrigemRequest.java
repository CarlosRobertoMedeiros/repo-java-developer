package br.com.roberto.ms_conversor.infraestructure.adapter.input;

import java.time.LocalDateTime;

public record JsonOrigemRequest(
        LocalDateTime date,
        String type,
        String origin,
        String status,
        BillingRequest billingRequest
) {
}
