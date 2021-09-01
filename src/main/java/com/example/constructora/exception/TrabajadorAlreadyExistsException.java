package com.example.constructora.exception;

public class TrabajadorAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TrabajadorAlreadyExistsException(final String message) {
        super(message);
    }
}
