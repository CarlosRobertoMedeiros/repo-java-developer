package br.com.roberto.hub_manager_app.domain.exceptions;

public class PaymentLinkBusinessException extends RuntimeException {

    private final PaymentLinkErrorCode paymentLinkErrorCode;

    public PaymentLinkBusinessException(PaymentLinkErrorCode paymentLinkErrorCode) {
        super(paymentLinkErrorCode.getDetail());
        this.paymentLinkErrorCode = paymentLinkErrorCode;
    }

    public String getCode() {
        return paymentLinkErrorCode.getCode();
    }

    public String getTitle() {
        return paymentLinkErrorCode.getTitle();
    }

    public String getType() {
        return "https://api.payment-link/errors/" + paymentLinkErrorCode.getCode().toLowerCase();
    }
}
