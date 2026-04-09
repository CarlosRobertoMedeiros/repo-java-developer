package br.com.roberto.hub_manager_app.domain.valueobject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Value Object representing a payment URL.
 * Encapsulates validation logic for URL format and consistency.
 */
public class PaymentUrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int MAX_LENGTH = 255;
    private final String value;

    public PaymentUrl(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment URL cannot be null or empty");
        }
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Payment URL exceeds maximum length of " + MAX_LENGTH);
        }
        if (!isValidUrl(value)) {
            throw new IllegalArgumentException("Payment URL format is invalid");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Validates basic URL structure (starts with http:// or https://)
     */
    private boolean isValidUrl(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentUrl that = (PaymentUrl) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PaymentUrl{" +
                "value='" + value + '\'' +
                '}';
    }
}
