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

    @NotNull(message = "The role name cannot be null")
    @NotBlank(message = "The role name cannot be blank")
    private String name;
}
