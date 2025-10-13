package br.com.roberto.br.samplehexagonal.ms_products.application.adapter.exceptions;

public class NotFoundInventoryException extends RuntimeException {
    public NotFoundInventoryException(String message) {
        super(message);
    }
}
