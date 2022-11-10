package com.servipac.almacen.rest.controller;

import com.servipac.almacen.rest.dto.request.RoleRequest;
import com.servipac.almacen.rest.dto.response.RoleResponse;
import com.servipac.almacen.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        RoleResponse role = roleService.findByRoleId(id);
        return ResponseEntity.ok(role);
    }

    @CrossOrigin
    @GetMapping(value = "/name/{name}", produces = "application/json")
    public ResponseEntity<RoleResponse> getRoleByName(@PathVariable String name) {
        RoleResponse role = roleService.findByName(name);
        return ResponseEntity.ok(role);
    }

    @CrossOrigin
    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest role) {
        RoleResponse roleResponse = roleService.create(role);
        return ResponseEntity.ok(roleResponse);
    }

    @CrossOrigin
    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<RoleResponse> updateRole(@RequestBody RoleRequest role, @PathVariable Long id) {
        RoleResponse roleResponse = roleService.update(role, id);
        return ResponseEntity.ok(roleResponse);
    }

    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
