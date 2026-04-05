package br.com.roberto.hub_manager_app.application.validator;

import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkBusinessException;
import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkErrorCode;
import br.com.roberto.hub_manager_app.domain.model.PaymentLinkModel;

import java.math.BigDecimal;

public class PaymentLinkValidator {

    public static void validate(PaymentLinkModel model) {

        requireNonNull(model);

        validateDescription(model.getDescription());
        validateAmount(model.getAmount());
    }

    private static void requireNonNull(PaymentLinkModel model) {
        if (model == null) {
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_REQUEST
            );
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_DESCRIPTION
            );
        }
    }

    private static void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentLinkBusinessException(
                    PaymentLinkErrorCode.INVALID_AMOUNT
            );
        }
    }
}