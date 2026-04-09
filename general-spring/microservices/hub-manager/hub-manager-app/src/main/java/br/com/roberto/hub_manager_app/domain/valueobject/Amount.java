package br.com.roberto.hub_manager_app.domain.valueobject;

import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkBusinessException;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkErrorCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Value Object representing a payment amount.
 * Encapsulates validation logic for positive amounts.
 */
public class Amount implements Serializable {

    private static final long serialVersionUID = 1L;
    private final BigDecimal value;

    public Amount(BigDecimal value) {
        if (value == null || value.signum() <= 0) {
            throw new PaymentLinkBusinessException(PaymentLinkErrorCode.INVALID_AMOUNT);
        }
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "value=" + value +
                '}';
    }
}
