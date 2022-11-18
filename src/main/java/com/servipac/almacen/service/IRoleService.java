package com.servipac.almacen.service;

import com.servipac.almacen.rest.dto.request.RoleRequest;
import com.servipac.almacen.rest.dto.response.RoleResponse;

import java.util.List;

public interface IRoleService {

    RoleResponse findByName(String name);
    RoleResponse findByRoleId(Long roleId);
    RoleResponse create(RoleRequest role);
    RoleResponse update(RoleRequest role, Long roleId);
    void delete(Long roleId);
   List<RoleResponse> findAll();


}


