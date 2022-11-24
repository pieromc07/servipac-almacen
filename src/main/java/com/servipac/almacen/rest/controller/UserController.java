package com.servipac.almacen.rest.controller;

import com.servipac.almacen.rest.dto.request.UpdateStatusRequest;
import com.servipac.almacen.rest.dto.request.UserRequest;
import com.servipac.almacen.rest.dto.response.UserResponse;
import com.servipac.almacen.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @CrossOrigin
    @GetMapping("users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        UserResponse user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @CrossOrigin
    @PostMapping(value = "/users/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest user) {
        UserResponse userResponse = userService.create(user);
        return ResponseEntity.ok(userResponse);
    }

    @CrossOrigin
    @PutMapping(value = "/users/update/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest user, @PathVariable Long id) {
        UserResponse userResponse = userService.update(id, user);
        return ResponseEntity.ok(userResponse);
    }

    @CrossOrigin
    @DeleteMapping(value = "/users/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/users/inactive")
    public ResponseEntity<?> findAllInactive() {
        return ResponseEntity.ok(userService.findAllStatusFalse());
    }

    @CrossOrigin
    @PutMapping(value = "/users/update/status/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UserResponse> updateStatus(@RequestBody @Valid UpdateStatusRequest statusRequest, @PathVariable Long id) {
        UserResponse userResponse = userService.updateStatus(id, statusRequest);
        return ResponseEntity.ok(userResponse);
    }
 }
