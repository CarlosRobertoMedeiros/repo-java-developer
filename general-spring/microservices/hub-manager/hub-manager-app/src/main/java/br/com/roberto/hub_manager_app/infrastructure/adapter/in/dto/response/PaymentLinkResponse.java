package br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentLinkResponse(
        @JsonProperty("id")
        UUID id,

        @JsonProperty("description")
        String description,

        @JsonProperty("amount")
        BigDecimal amount,

        @JsonProperty("paymentUrl")
        String paymentUrl,

        @JsonProperty("status")
        String status,

        @JsonProperty("creationDate")
        LocalDateTime creationDate,

        @JsonProperty("updatedDate")
        LocalDateTime updatedDate,

        @JsonProperty("expirationDate")
        LocalDateTime expirationDate
) {}
