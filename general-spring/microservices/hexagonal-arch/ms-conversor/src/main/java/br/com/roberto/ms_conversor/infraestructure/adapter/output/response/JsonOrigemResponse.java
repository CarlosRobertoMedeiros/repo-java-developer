package br.com.roberto.ms_conversor.infraestructure.adapter.output;

import java.time.LocalDateTime;

public record JsonOrigemResponse(LocalDateTime date,
                                 String type,
                                 String origin,
                                 String status,
                                 BillingOrigemResponse billingOrigemResponse) {
}
