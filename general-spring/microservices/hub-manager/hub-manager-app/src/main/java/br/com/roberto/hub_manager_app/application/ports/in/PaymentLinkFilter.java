package br.com.roberto.hub_manager_app.application.ports.in;

import java.time.LocalDateTime;

public record PaymentLinkFilter(
        LocalDateTime createdAtFrom,
        LocalDateTime createdAtTo,
        Boolean isActive,
        String description
) {}
