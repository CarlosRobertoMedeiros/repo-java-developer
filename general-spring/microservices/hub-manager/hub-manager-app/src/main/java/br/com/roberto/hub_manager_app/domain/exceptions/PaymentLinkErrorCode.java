package br.com.roberto.hub_manager_app.domain.exceptions;

import org.springframework.http.HttpStatus;

public enum PaymentLinkErrorCode {

    INVALID_ID("Invalid ID", "Id for update must not be null", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST("Invalid Request", "Request must not be null", HttpStatus.BAD_REQUEST),
    INVALID_DESCRIPTION("Invalid Description", "Description is required", HttpStatus.BAD_REQUEST),
    INVALID_AMOUNT("Invalid Amount", "Amount must be greater than zero", HttpStatus.BAD_REQUEST),
    INVALID_EXPIRATION_DATE("Invalid Expiration Date", "Expiration date cannot be in the past", HttpStatus.BAD_REQUEST),
    INVALID_STATUS("Invalid Status", "Invalid status", HttpStatus.BAD_REQUEST),
    INVALID_STATUS_TRANSITION("Invalid Status Transition", "Invalid status change", HttpStatus.UNPROCESSABLE_ENTITY),
    PAYMENT_LINK_NOT_FOUND("PaymentLink Not Found", "Payment link not found", HttpStatus.NOT_FOUND),
    PAYMENT_LINK_INACTIVE("PaymentLink Inactive", "Inactive payment link cannot be modified", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String title;
    private final String detail;
    private final HttpStatus httpStatus;

    PaymentLinkErrorCode(String title, String detail, HttpStatus httpStatus) {
        this.title = title;
        this.detail = detail;
        this.httpStatus = httpStatus;
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

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
