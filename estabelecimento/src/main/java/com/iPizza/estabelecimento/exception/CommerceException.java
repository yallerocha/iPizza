package com.iPizza.estabelecimento.exception;

public class CommerceException extends RuntimeException {
    
    public CommerceException() {
        super("Erro inesperado no AppCommerce!");
    }

    public CommerceException(String message) {
        super(message);
    }
}

