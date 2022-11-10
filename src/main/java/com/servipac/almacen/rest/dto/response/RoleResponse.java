package com.servipac.almacen.rest.dto.response;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {

    private Long id;

    private String name;

    private Boolean state;
}
