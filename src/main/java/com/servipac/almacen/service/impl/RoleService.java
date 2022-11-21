package com.servipac.almacen.service.impl;

import com.servipac.almacen.exception.AlreadyExistsException;
import com.servipac.almacen.exception.DefaultException;
import com.servipac.almacen.exception.NotFoundException;
import com.servipac.almacen.mapper.IRoleMapper;
import com.servipac.almacen.persistence.model.Role;
import com.servipac.almacen.persistence.model.User;
import com.servipac.almacen.persistence.repository.RoleRepository;
import com.servipac.almacen.persistence.repository.UserRepository;
import com.servipac.almacen.rest.dto.request.RoleRequest;
import com.servipac.almacen.rest.dto.response.RoleResponse;
import com.servipac.almacen.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IRoleMapper roleMapper;
    @Override
    public RoleResponse findByName(String name) {
        Role role = roleRepository.findByName("ROLE"+name.toUpperCase());
        return roleMapper.toResponse(role);
    }
    @Override
    public RoleResponse findByRoleId(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("El Rol no existe"));
        System.out.println(role.toString());
        return roleMapper.toResponse(role);
    }
    @Override
    public RoleResponse create(RoleRequest role) {
        Role entity = roleMapper.toEntity(role);
        if (existsRole(entity.getName())) {
            throw new AlreadyExistsException("El Rol " + entity.getDescription() + " ya existe");
        }
        entity.builder()
                .state(true)
                .build();
        entity = roleRepository.save(entity);
        return roleMapper.toResponse(entity);
    }
    @Override
    public RoleResponse update(RoleRequest roleRequest, Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("El Rol no existe"));
        if(!equalsRole(role.getDescription(), roleRequest.getName())) {
            if (existsRole(roleRequest.getName())) {
                throw new AlreadyExistsException("El Rol " + roleRequest.getName() + " ya existe");
            }
        }
        Role entity = roleMapper.toEntity(roleRequest);
        entity.setRoleId(role.getRoleId());
        roleRepository.save(entity);
        return roleMapper.toResponse(entity);
    }
    @Override
    public void delete(Long roleId) {
        Role entity = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("El Rol no existe"));
        List<User> users = userRepository.findByRole(entity);
        if(users.size() > 0){
            throw new DefaultException("El Rol " + entity.getDescription() + " no se puede eliminar porque tiene usuarios asociados");
        }
        roleRepository.delete(entity);
    }
    @Override
    public List<RoleResponse> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleResponse> roleResponses = new ArrayList<>();
        roles.forEach(role -> {
            roleResponses.add(roleMapper.toResponse(role));
        });
        return roleResponses;
    }
    private Boolean existsRole(String name){
        return roleRepository.existsRoleByDescription(name.toUpperCase());
    }
    private Boolean equalsRole(String name, String requestName){
        return name.equals(requestName.toUpperCase());
    }

}
