package com.servipac.almacen.rest.controller;

import com.servipac.almacen.rest.dto.request.RoleRequest;
import com.servipac.almacen.rest.dto.response.RoleResponse;
import com.servipac.almacen.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @CrossOrigin
    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponse>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @CrossOrigin
    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        RoleResponse role = roleService.findByRoleId(id);
        return ResponseEntity.ok(role);
    }

    @CrossOrigin
    @PostMapping(value = "/roles/create", produces = "application/json")
    public ResponseEntity<RoleResponse> createRole(@RequestBody @Valid RoleRequest role) {
        RoleResponse roleResponse = roleService.create(role);
        return ResponseEntity.ok(roleResponse);
    }

    @CrossOrigin
    @PutMapping(value = "/roles/update/{id}", produces = "application/json")
    public ResponseEntity<RoleResponse> updateRole(@RequestBody @Valid RoleRequest role, @PathVariable Long id) {
        RoleResponse roleResponse = roleService.update(role, id);
        return ResponseEntity.ok(roleResponse);
    }

    @CrossOrigin
    @DeleteMapping(value = "/roles/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
