package br.com.roberto.hub_manager_app.infrastructure.adapter.out.entity;

import br.com.roberto.hub_manager_app.domain.model.PaymentLinkStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA Entity for Payment Link persistence.
 * Maps to TB_PAYMENT_LINK table in the database.
 */
@Entity
@Table(name = "TB_PAYMENT_LINK", indexes = {
        @Index(name = "idx_payment_link_created_at", columnList = "created_at"),
        @Index(name = "idx_payment_link_is_active", columnList = "is_active"),
        @Index(name = "idx_payment_link_payment_url", columnList = "payment_url", unique = true)
})
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

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "payment_url", length = 255, unique = true)
    private String paymentUrl;

    @Enumerated(EnumType.STRING)
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.NAMED_ENUM)
    @Column(name = "payment_link_status", columnDefinition = "hub_manager.status_type")
    private PaymentLinkStatus paymentLinkStatus;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
