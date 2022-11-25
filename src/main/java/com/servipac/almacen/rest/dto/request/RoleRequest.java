package com.servipac.almacen.rest.dto.request;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {

    @NotNull(message = "El nombre del rol es obligatorio")
    @NotBlank(message = "El nombre del rol no puede estar vacío")
    private String name;
}
