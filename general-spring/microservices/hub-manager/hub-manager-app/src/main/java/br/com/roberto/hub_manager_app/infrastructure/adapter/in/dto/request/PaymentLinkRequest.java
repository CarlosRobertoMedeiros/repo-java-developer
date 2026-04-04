package br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentLinkRequest(
        String description,
        BigDecimal amount,
        LocalDateTime expirationDate
) {}
