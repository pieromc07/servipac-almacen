package com.servipac.almacen.service.impl;

import com.servipac.almacen.exception.InvalidCredentialsException;
import com.servipac.almacen.exception.NotFoundException;
import com.servipac.almacen.mapper.IUserMapper;
import com.servipac.almacen.persistence.model.User;
import com.servipac.almacen.persistence.repository.UserRepository;
import com.servipac.almacen.rest.dto.request.LoginRequest;
import com.servipac.almacen.rest.dto.response.LoginResponse;
import com.servipac.almacen.security.common.JwtUtils;
import com.servipac.almacen.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public LoginResponse login(LoginRequest user) {
        authenticate(user);
        User userEntity = getUserBy(user.getUsername().toUpperCase());
        LoginResponse response = userMapper.toLoginResponse(userEntity);
        response.setRole(userEntity.getRole().getDescription());
        response.setToken(jwtUtils.generateToken(userEntity));
        return response;
    }

    private User getUserBy(String username) {
        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new NotFoundException("Usuario no encontrado");
        }
        return userEntity;
    }

    private void authenticate(LoginRequest user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername().toUpperCase(),
                    user.getPassword()));
        } catch (Exception e) {
            throw new InvalidCredentialsException("nombre de usuario o contraseña incorrectos");
        }
    }
}
