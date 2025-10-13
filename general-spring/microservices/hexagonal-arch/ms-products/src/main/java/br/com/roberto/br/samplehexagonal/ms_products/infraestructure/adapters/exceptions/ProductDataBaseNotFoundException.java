package br.com.roberto.br.samplehexagonal.ms_products.infraestructure.adapters.exceptions;

public class ProductDataBaseNotFoundException extends RuntimeException{
    public ProductDataBaseNotFoundException(String message) {
        super(message);
    }
}
