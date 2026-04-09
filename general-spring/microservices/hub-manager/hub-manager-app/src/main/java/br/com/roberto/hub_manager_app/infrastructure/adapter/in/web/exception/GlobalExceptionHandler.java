package br.com.roberto.hub_manager_app.infrastructure.adapter.in.web.exception;

import br.com.roberto.hub_manager_app.domain.exceptions.PaymentLinkBusinessException;
import br.com.roberto.hub_manager_app.infrastructure.common.RequestIdMDC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String REQUEST_ID = "X-Request-ID";

    @ExceptionHandler(PaymentLinkBusinessException.class)
    public ResponseEntity<ErrorResponse> handlePaymentLinkBusinessException(
            PaymentLinkBusinessException ex,
            WebRequest request) {

        String requestId = extractRequestId(request);
        log.warn("Business exception occurred - Code: {} - Message: {} - RequestId: {}",
                ex.getCode(), ex.getMessage(), requestId);

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getCode(),
                ex.getTitle(),
                ex.getMessage(),
                requestId
        );

        // This would require passing the error code, for now we'll map based on code string
        HttpStatus status = mapErrorCodeToStatus(ex.getCode());

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }

    private HttpStatus mapErrorCodeToStatus(String errorCode) {
        return switch (errorCode) {
            case "PAYMENT_LINK_NOT_FOUND" -> HttpStatus.NOT_FOUND;
            case "INVALID_STATUS_TRANSITION", "PAYMENT_LINK_INACTIVE" -> HttpStatus.UNPROCESSABLE_ENTITY;
            default -> HttpStatus.BAD_REQUEST;
        };
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        String requestId = extractRequestId(request);
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .collect(Collectors.joining(", "));

        log.warn("Validation exception occurred - Message: {} - RequestId: {}", message, requestId);

        ErrorResponse errorResponse = new ErrorResponse(
                "VALIDATION_ERROR",
                "Invalid Request",
                message,
                requestId
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            WebRequest request) {

        String requestId = extractRequestId(request);
        log.error("Unexpected exception occurred - Type: {} - Message: {} - RequestId: {}",
                ex.getClass().getSimpleName(), ex.getMessage(), requestId, ex);

        ErrorResponse errorResponse = new ErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "Internal Server Error",
                "An unexpected error occurred. Please contact support with the request ID: " + requestId,
                requestId
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    private String extractRequestId(WebRequest request) {
        String requestId = RequestIdMDC.get();
        return requestId != null ? requestId : "NO-ID";
    }
}
