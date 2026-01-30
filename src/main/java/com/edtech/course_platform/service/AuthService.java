package com.edtech.course_platform.service;

import com.edtech.course_platform.dto.LoginRequest;
import com.edtech.course_platform.dto.LoginResponse;
import com.edtech.course_platform.dto.RegisterRequest;
import com.edtech.course_platform.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
