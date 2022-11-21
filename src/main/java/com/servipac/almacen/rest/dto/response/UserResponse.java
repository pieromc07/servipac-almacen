package com.servipac.almacen.rest.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponse {

    private String username;
    private String email;
    private String role;
}
