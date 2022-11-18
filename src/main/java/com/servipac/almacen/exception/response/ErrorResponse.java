package com.servipac.almacen.exception.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ErrorResponse {

    private Integer statusCode;
    private String message;
    private List<String> moreInfo;
}
