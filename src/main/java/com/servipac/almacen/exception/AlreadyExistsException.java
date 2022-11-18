package com.servipac.almacen.exception;

public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -3909699168058965155L;

    public AlreadyExistsException(String message) {
        super(message);
    }
}
