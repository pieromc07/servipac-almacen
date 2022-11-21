package com.servipac.almacen.mapper;

import com.servipac.almacen.persistence.model.User;
import com.servipac.almacen.rest.dto.request.LoginRequest;
import com.servipac.almacen.rest.dto.request.UserRequest;
import com.servipac.almacen.rest.dto.response.LoginResponse;
import com.servipac.almacen.rest.dto.response.UserResponse;

public interface IUserMapper {

    User toEntity(LoginRequest loginRequest);
    LoginResponse toLoginResponse(User user);
    User toUser(UserRequest userRequest);
    UserResponse toUserResponse(User user);

}
