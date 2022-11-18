package com.servipac.almacen.mapper;

import com.servipac.almacen.persistence.model.User;
import com.servipac.almacen.rest.dto.request.LoginRequest;
import com.servipac.almacen.rest.dto.response.LoginResponse;

public interface IUserMapper {

    User toEntity(LoginRequest loginRequest);
    LoginResponse toLoginResponse(User user);
}
