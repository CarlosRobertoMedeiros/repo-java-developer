package br.com.roberto.hub_manager_app.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder pattern for constructing PaymentLinkModel instances.
 * Simplifies object creation and improves readability.
 */
public class PaymentLinkModelBuilder {

    private UUID id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime expirationDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String paymentUrl;
    private PaymentLinkStatus status;
    private Boolean isActive;

    public PaymentLinkModelBuilder() {
    }

    public PaymentLinkModelBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public PaymentLinkModelBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PaymentLinkModelBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public PaymentLinkModelBuilder withExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public PaymentLinkModelBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public PaymentLinkModelBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public PaymentLinkModelBuilder withPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
        return this;
    }

    public PaymentLinkModelBuilder withStatus(PaymentLinkStatus status) {
        this.status = status;
        return this;
    }

    public PaymentLinkModelBuilder withIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public PaymentLinkModel build() {
        PaymentLinkModel model = new PaymentLinkModel();
        model.setId(id);
        model.setDescription(description);
        model.setAmount(amount);
        model.setExpirationDate(expirationDate);
        model.setCreatedAt(createdAt);
        model.setUpdatedAt(updatedAt);
        model.setPaymentUrl(paymentUrl);
        model.setStatus(status);
        model.setActive(isActive);
        return model;
    }
}
