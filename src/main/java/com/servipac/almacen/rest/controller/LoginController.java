package com.servipac.almacen.rest.controller;

import com.servipac.almacen.rest.dto.request.LoginRequest;
import com.servipac.almacen.rest.dto.response.LoginResponse;
import com.servipac.almacen.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        if(loginResponse != null) {
            return ResponseEntity.ok(loginResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
