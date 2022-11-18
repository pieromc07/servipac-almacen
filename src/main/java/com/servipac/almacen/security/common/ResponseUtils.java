package com.servipac.almacen.security.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servipac.almacen.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    private ResponseUtils() {}
    public static void setCustomForbiddenResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(getGenericErrorResponse()));
    }
    private static ErrorResponse getGenericErrorResponse() {
        return ErrorResponse.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message("No tiene permisos para acceder a este recurso")
                .build();
    }
}
