package com.servipac.almacen.mapper.impl;

import com.servipac.almacen.mapper.IUserMapper;
import com.servipac.almacen.persistence.model.User;
import com.servipac.almacen.rest.dto.request.LoginRequest;
import com.servipac.almacen.rest.dto.request.UserRequest;
import com.servipac.almacen.rest.dto.response.LoginResponse;
import com.servipac.almacen.rest.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IUserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User toEntity(LoginRequest loginRequest) {
        return User.builder()
                .username(loginRequest.getUsername().toUpperCase())
                .password(passwordEncoder.encode(loginRequest.getPassword()))
                .build();
    }

    @Override
    public LoginResponse toLoginResponse(User user) {
        return LoginResponse.builder()
                .name(user.getUsername())
                .build();
    }
    @Override
    public User toUser(UserRequest userRequest) {
        return User.builder()
                .username(userRequest.getUsername().toUpperCase())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().getDescription())
                .build();
    }
}
