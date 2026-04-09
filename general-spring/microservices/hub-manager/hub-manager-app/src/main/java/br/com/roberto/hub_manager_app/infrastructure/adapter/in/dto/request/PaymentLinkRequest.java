package br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PaymentLinkRequest(
        @NotBlank(message = "Description is required and cannot be blank")
        @Size(min = 1, max = 100, message = "Description must have between 1 and 100 characters")
        String description,

        @NotBlank(message = "Amount is required")
        @Positive(message = "Amount must be a positive value")
        BigDecimal amount
) {
}
