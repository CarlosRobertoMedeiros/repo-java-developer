package br.com.roberto.hub_manager_app.domain.exceptions;

public enum PaymentLinkErrorCode {

    INVALID_ID("Invalid ID", "Id for update must not be null"),
    INVALID_REQUEST("Invalid Request", "Request must not be null"),
    INVALID_DESCRIPTION("Invalid Description", "Description is required"),
    INVALID_AMOUNT("Invalid Amount", "Amount must be greater than zero"),
    INVALID_EXPIRATION_DATE("Invalid Expiration Date", "Expiration date cannot be in the past"),
    INVALID_STATUS("Invalid Status", "Invalid status"),
    INVALID_STATUS_TRANSITION("Invalid Status Transition", "Invalid status change"),
    PAYMENT_LINK_NOT_FOUND("PaymentLink Not Found", "Payment link not found"),
    PAYMENT_LINK_INACTIVE("PaymentLink Inactive", "Inactive payment link cannot be modified");

    private final String title;
    private final String detail;

    PaymentLinkErrorCode(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public String getCode() {
        return this.name();
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }
}
