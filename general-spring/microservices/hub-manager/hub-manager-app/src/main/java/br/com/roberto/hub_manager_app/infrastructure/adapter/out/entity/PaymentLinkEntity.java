package br.com.roberto.hub_manager_app.infrastructure.adapter.out.entity;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_PAYMENT_LINK")
public class PaymentLinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "payment_url", length = 255)
    private String paymentUrl;
    @Enumerated(EnumType.STRING)
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.NAMED_ENUM)
    @Column(name = "payment_link_status", columnDefinition = "hub_manager.status_type")
    private PaymentLinkStatus paymentLinkStatus;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    // =========================================================
    // CONSTRUCTORS
    // =========================================================

    public PaymentLinkEntity() {
    }

    public PaymentLinkEntity(UUID id,
                             String description,
                             BigDecimal amount,
                             LocalDateTime expirationDate,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt,
                             String paymentUrl,
                             PaymentLinkStatus status,
                             Boolean active) {

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.expirationDate = expirationDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.paymentUrl = paymentUrl;
        this.paymentLinkStatus = status;
        this.isActive = active;
    }

    // GETTERS AND SETTERS

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

    public void setStatus(PaymentLinkStatus paymentLinkStatus) {
        this.paymentLinkStatus = paymentLinkStatus;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }
}


