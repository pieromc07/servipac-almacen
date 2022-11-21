package com.servipac.almacen.rest.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
public class UserRequest {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;
    @Email(message = "El email no es válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
    @NotBlank(message = "El rol es obligatorio")
    private Long roleId;
}
