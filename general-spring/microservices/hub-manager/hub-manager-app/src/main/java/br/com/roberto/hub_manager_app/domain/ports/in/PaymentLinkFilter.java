package br.com.roberto.hub_manager_app.domain.ports.in;

import java.time.LocalDateTime;

public record PaymentLinkFilter(
        LocalDateTime createdAtTo,
        LocalDateTime createdAtFrom,
        Boolean isActive,
        String description
) {}
