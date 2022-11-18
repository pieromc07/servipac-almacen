package com.servipac.almacen.exception;

import com.servipac.almacen.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final String DEFAULT_ERROR_MESSAGE = "Ha ocurrido un error inesperado";
    private static final String ERROR_MESSAGE_NOT_FOUND = "No se ha encontrado el recurso solicitado";
    private static final String ERROR_MESSAGE_ALREADY_EXISTS = "El recurso ya existe";
    private static final String ERROR_MESSAGE_IN_USE = "El recurso esta en uso";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = buildError(HttpStatus.BAD_REQUEST,
                DEFAULT_ERROR_MESSAGE,
                e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse errorResponse = buildError(HttpStatus.NOT_FOUND,
                ERROR_MESSAGE_NOT_FOUND,
                e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException e) {
        ErrorResponse errorResponse = buildError(HttpStatus.CONFLICT,
                ERROR_MESSAGE_ALREADY_EXISTS,
                e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = DefaultException.class)
    public ResponseEntity<ErrorResponse> handleInUseException(DefaultException e) {
        ErrorResponse errorResponse = buildError(HttpStatus.CONFLICT,
                ERROR_MESSAGE_IN_USE,
                e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    private ErrorResponse buildError(HttpStatus httpStatus, String message, List<String> moreInfo) {
        return ErrorResponse.builder()
                .statusCode(httpStatus.value())
                .message(message)
                .moreInfo(moreInfo)
                .build();
    }

    private ErrorResponse buildError(HttpStatus httpStatus, String message, String moreInfo) {
        return buildError(httpStatus, message, List.of(moreInfo));
    }
}
