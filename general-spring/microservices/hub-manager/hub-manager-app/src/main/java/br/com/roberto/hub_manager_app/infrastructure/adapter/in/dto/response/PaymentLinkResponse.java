package br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentLinkResponse(
        UUID id,
        String description,
        BigDecimal amount,
        String paymentUrl,
        String status,
        LocalDateTime creationDate,
        LocalDateTime expirationDate
) {}
