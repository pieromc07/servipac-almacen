package com.servipac.almacen.mapper.impl;

import com.servipac.almacen.mapper.IRoleMapper;
import com.servipac.almacen.persistence.model.Role;
import com.servipac.almacen.rest.dto.request.RoleRequest;
import com.servipac.almacen.rest.dto.response.RoleResponse;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements IRoleMapper {

    @Override
    public RoleResponse toResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getRoleId())
                .name(role.getName())
                .state(role.getState())
                .build();
    }

    @Override
    public Role toEntity(RoleRequest roleRequest) {
        return Role.builder()
                .description(roleRequest.getName().toUpperCase())
                .build();
    }
}
