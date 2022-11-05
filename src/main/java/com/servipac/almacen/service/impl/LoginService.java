package com.servipac.almacen.service.impl;

import com.servipac.almacen.persistence.model.User;
import com.servipac.almacen.persistence.repository.UserRepository;
import com.servipac.almacen.rest.dto.request.LoginRequest;
import com.servipac.almacen.rest.dto.response.LoginResponse;
import com.servipac.almacen.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername());

        if(user != null) {
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return LoginResponse.builder()
                        .username(user.getUsername())
                        .build();
            }
        }

        return null;
    }
}
