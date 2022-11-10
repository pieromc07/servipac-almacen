package com.servipac.almacen.service.impl;

import com.servipac.almacen.mapper.IRoleMapper;
import com.servipac.almacen.persistence.model.Role;
import com.servipac.almacen.persistence.repository.RoleRepository;
import com.servipac.almacen.rest.dto.request.RoleRequest;
import com.servipac.almacen.rest.dto.response.RoleResponse;
import com.servipac.almacen.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public RoleResponse findByName(String name) {
        Role role = roleRepository.findByName("ROLE"+name.toUpperCase());
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse findByRoleId(Long roleId) {
        Role role = roleRepository.findByRoleId(roleId);
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse create(RoleRequest role) {
        Role entity = roleMapper.toEntity(role);
        entity.builder()
                .name("ROLE_"+role.getName().toUpperCase())
                .state(true)
                .build();
        entity = roleRepository.save(entity);
        return roleMapper.toResponse(entity);
    }

    @Override
    public RoleResponse update(RoleRequest role, Long roleId) {
        Role entity = roleRepository.findByRoleId(roleId);
        entity.builder()
                .description(role.getName().toUpperCase())
                .name("ROLE_"+role.getName().toUpperCase())
                .build();
        entity = roleRepository.save(entity);
        return roleMapper.toResponse(entity);
    }

    @Override
    public void delete(Long roleId) {
        Role entity = roleRepository.findByRoleId(roleId);
        entity.setState(false);
        roleRepository.save(entity);
    }
}
