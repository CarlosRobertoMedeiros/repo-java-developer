package br.com.roberto.hub_manager_app.domain.valueobject;

import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkBusinessException;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkErrorCode;

import java.io.Serializable;
import java.util.Objects;

/**
 * Value Object representing a payment link description.
 * Encapsulates validation logic for non-empty, length-constrained descriptions.
 */
public class Description implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int MAX_LENGTH = 100;
    private final String value;

    public Description(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new PaymentLinkBusinessException(PaymentLinkErrorCode.INVALID_DESCRIPTION);
        }
        if (value.length() > MAX_LENGTH) {
            throw new PaymentLinkBusinessException(PaymentLinkErrorCode.INVALID_DESCRIPTION);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Description{" +
                "value='" + value + '\'' +
                '}';
    }
}
