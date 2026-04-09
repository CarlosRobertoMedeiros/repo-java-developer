package br.com.roberto.hub_manager_app.domain.model;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Domain model representing a Payment Link.
 * Encapsulates payment link business logic and state management.
 */
@Slf4j
public class PaymentLinkModel {
    private UUID id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime expirationDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String paymentUrl;
    private PaymentLinkStatus paymentLinkStatus;
    private Boolean isActive;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public PaymentLinkStatus getStatus() {
        return paymentLinkStatus;
    }

    public void setStatus(PaymentLinkStatus status) {
        this.paymentLinkStatus = status;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    /**
     * Domain method: Checks if this payment link can be updated.
     * A payment link can only be updated if it's active and not expired.
     */
    public boolean canBeUpdated() {
        if (!Boolean.TRUE.equals(this.isActive)) {
            log.debug("Payment link cannot be updated - not active");
            return false;
        }

        if (this.paymentLinkStatus == PaymentLinkStatus.EXPIRED) {
            log.debug("Payment link cannot be updated - expired");
            return false;
        }

        return true;
    }

    /**
     * Domain method: Checks if this payment link can be disabled.
     * A payment link can only be disabled if it's in ACTIVE status.
     */
    public boolean canBeDisabled() {
        return this.paymentLinkStatus == PaymentLinkStatus.ACTIVE;
    }

    /**
     * Domain method: Checks if this payment link has expired.
     * Compares the expiration date with the current time.
     */
    public boolean isExpired() {
        if (this.expirationDate == null) {
            return false;
        }
        return LocalDateTime.now().isAfter(this.expirationDate);
    }

    /**
     * Domain method: Gets the effective payment status considering expiration.
     * If the link is active but expired, the status should be EXPIRED.
     */
    public PaymentLinkStatus getEffectiveStatus() {
        if (Boolean.TRUE.equals(this.isActive) && this.isExpired()) {
            return PaymentLinkStatus.EXPIRED;
        }
        return this.paymentLinkStatus;
    }

    /**
     * Factory method: Creates a new PaymentLinkModel with default values.
     */
    public static PaymentLinkModelBuilder builder() {
        return new PaymentLinkModelBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PaymentLinkModel that = (PaymentLinkModel) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(amount, that.amount) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(paymentUrl, that.paymentUrl) && Objects.equals(paymentLinkStatus, that.paymentLinkStatus) && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, amount, expirationDate, createdAt, updatedAt, paymentUrl, paymentLinkStatus, isActive);
    }

    @Override
    public String toString() {
        return "PaymentLinkModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", expirationDate=" + expirationDate +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", paymentUrl='" + paymentUrl + '\'' +
                ", paymentLinkStatus='" + paymentLinkStatus + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
