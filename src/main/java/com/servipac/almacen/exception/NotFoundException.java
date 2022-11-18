package com.servipac.almacen.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4909699168058965155L;

    public NotFoundException(String message) {
        super(message);
    }

}