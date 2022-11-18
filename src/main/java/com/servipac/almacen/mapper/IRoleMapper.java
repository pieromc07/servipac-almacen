package com.servipac.almacen.mapper;

import com.servipac.almacen.persistence.model.Role;
import com.servipac.almacen.rest.dto.request.RoleRequest;
import com.servipac.almacen.rest.dto.response.RoleResponse;

public interface IRoleMapper {

    RoleResponse toResponse(Role role);
    Role toEntity(RoleRequest roleRequest);

}
