package com.servipac.almacen.service;

import com.servipac.almacen.rest.dto.request.UpdateStatusRequest;
import com.servipac.almacen.rest.dto.request.UserRequest;
import com.servipac.almacen.rest.dto.response.UserResponse;

import java.util.List;

public interface IUserService {

    UserResponse create(UserRequest userRequest);
    List<UserResponse> findAll();
    UserResponse findById(Long id);
    UserResponse update(Long id, UserRequest userRequest);
    void delete(Long id);
    List<UserResponse> findAllStatusFalse();
    UserResponse updateStatus(Long id, UpdateStatusRequest updateStatusRequest);
}
