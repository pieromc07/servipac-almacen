package com.servipac.almacen.service;

import com.servipac.almacen.rest.dto.request.LoginRequest;
import com.servipac.almacen.rest.dto.response.LoginResponse;

public interface ILoginService {

    LoginResponse login(LoginRequest loginRequest);
}
