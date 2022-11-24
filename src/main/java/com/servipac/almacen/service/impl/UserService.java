package com.servipac.almacen.service.impl;

import com.servipac.almacen.exception.AlreadyExistsException;
import com.servipac.almacen.exception.NotFoundException;
import com.servipac.almacen.mapper.IUserMapper;
import com.servipac.almacen.persistence.model.User;
import com.servipac.almacen.persistence.repository.RoleRepository;
import com.servipac.almacen.persistence.repository.UserRepository;
import com.servipac.almacen.rest.dto.request.UpdateStatusRequest;
import com.servipac.almacen.rest.dto.request.UserRequest;
import com.servipac.almacen.rest.dto.response.UserResponse;
import com.servipac.almacen.security.common.JwtUtils;
import com.servipac.almacen.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private IUserMapper userMapper;

    @Override
    public UserResponse create(UserRequest userRequest) {
        if(existsByUsername(userRequest.getUsername()) || existsByEmail(userRequest.getEmail())){
            throw new AlreadyExistsException("El usuario ya existe");
        }
        if(roleRepository.findByRoleId(userRequest.getRole()) == null) {
            throw new NotFoundException("El rol no existe");
        }
        User user = userMapper.toUser(userRequest);
        user.setStatus(true);
        user.setRole(roleRepository.findByRoleId(userRequest.getRole()));
        return userMapper.toUserResponse(userRepository.save(user));
    }
    @Override
    public List<UserResponse> findAll() {
        List<UserResponse> users = new ArrayList<>();
        userRepository.findAllByStatusIsTrue().forEach(user -> users.add(userMapper.toUserResponse(user)));
        return users;
    }
    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("El usuario no existe"));
        return userMapper.toUserResponse(user);
    }
    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("El usuario no existe"));
        if(!equalsEmail(user.getEmail(), userRequest.getEmail())) {
            if(userRepository.existsByEmail(userRequest.getEmail())) {
                throw new AlreadyExistsException("El email " + userRequest.getEmail() + " ya existe");
            }
        }

        if(!equalsUsername(user.getUsername(), userRequest.getUsername())) {
            if(userRepository.existsByUsername(userRequest.getUsername())) {
                throw new AlreadyExistsException("El usuario " + userRequest.getUsername() + " ya existe");
            }
        }

        if(roleRepository.findByRoleId(userRequest.getRole()) == null) {
            throw new NotFoundException("El rol no existe");
        }
        user.setUsername(userRequest.getUsername().toUpperCase());
        user.setEmail(userRequest.getEmail());
        user.setRole(roleRepository.findByRoleId(userRequest.getRole()));
        return userMapper.toUserResponse(userRepository.save(user));
    }
    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("El usuario no existe"));
        user.setStatus(false);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> findAllStatusFalse() {
        List<UserResponse> users = new ArrayList<>();
        userRepository.findAllByStatusIsFalse().forEach(user -> users.add(userMapper.toUserResponse(user)));
        return users;
    }

    @Override
    public UserResponse updateStatus(Long id, UpdateStatusRequest updateStatusRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("El usuario no existe"));
        user.setStatus(updateStatusRequest.getStatus());
        return userMapper.toUserResponse(userRepository.save(user));
    }


    private Boolean  existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    private Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private Boolean equalsUsername(String username, String requestUsername) {
        return username.equals(requestUsername);
    }

    private Boolean equalsEmail(String email, String requestEmail) {
        return email.equals(requestEmail);
    }

}
