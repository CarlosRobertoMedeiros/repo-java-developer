package br.com.roberto.hub_manager_app.domain.model;

import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkBusinessException;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkErrorCode;

import java.util.Arrays;

public enum PaymentLinkStatus {
    EXPIRED("EXPIRED"),
    PENDING("PENDING"),
    ACTIVE("ACTIVE"),
    DISABLED("DISABLED");

    private final String description;

    PaymentLinkStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentLinkStatus from(String value) {
        return Arrays.stream(values())
                .filter(status -> status.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new PaymentLinkBusinessException(
                                PaymentLinkErrorCode.INVALID_STATUS
                        )
                );
    }

    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isExpired() {
        return this == EXPIRED;
    }

    public boolean isDisabled() {
        return this == DISABLED;
    }

    public boolean canBeUpdated() {
        return this == PENDING || this == ACTIVE;
    }

    public boolean canBeDisabled() {
        return this == ACTIVE;
    }

    public boolean canExpire() {
        return this == PENDING || this == ACTIVE;
    }



}
