package com.banconova.web;

import com.banconova.common.ApiResponse;
import com.banconova.dto.auth.*;
import com.banconova.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService auth;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest req){
        return auth.register(req);
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest req){
        return auth.login(req);
    }

    @GetMapping("/me")
    public ApiResponse<UserMe> me(Authentication authentication){
        return auth.me(authentication.getName());
    }
}
