package br.com.roberto.hub_manager_app.infrastructure.adapter.in.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
    String code,
    String title,
    String message,
    String timestamp,
    String requestId
) {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public ErrorResponse(String code, String title, String message, String requestId) {
        this(code, title, message, LocalDateTime.now().format(formatter), requestId);
    }

    public ErrorResponse(String code, String title, String message) {
        this(code, title, message, LocalDateTime.now().format(formatter), null);
    }
}
