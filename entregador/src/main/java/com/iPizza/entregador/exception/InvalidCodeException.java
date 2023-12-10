package com.iPizza.entregador.exception;

public class InvalidCodeException extends RuntimeException {
    
    public InvalidCodeException() {
        super("Código inválido!");
    }

    public InvalidCodeException(String message) {
        super(message);
    }
}

