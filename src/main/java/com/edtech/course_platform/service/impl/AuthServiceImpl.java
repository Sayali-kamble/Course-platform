package com.edtech.course_platform.service.impl;

import com.edtech.course_platform.dto.LoginRequest;
import com.edtech.course_platform.dto.LoginResponse;
import com.edtech.course_platform.dto.RegisterRequest;
import com.edtech.course_platform.dto.RegisterResponse;
import com.edtech.course_platform.entity.User;
import com.edtech.course_platform.exception.InvalidCredentialsException;
import com.edtech.course_platform.exception.UserAlreadyExistsException;
import com.edtech.course_platform.repository.UserRepository;
import com.edtech.course_platform.security.JwtUtil;
import com.edtech.course_platform.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                "User registered successfully.."
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email"));


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");

        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(
                token,
                user.getEmail(),
                expiration
        );
    }
}