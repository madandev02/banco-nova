package com.banconova.service;

import com.banconova.common.ApiResponse;
import com.banconova.domain.User;
import com.banconova.domain.enums.Role;
import com.banconova.dto.auth.*;
import com.banconova.repo.UserRepository;
import com.banconova.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public ApiResponse<AuthResponse> register(RegisterRequest req){
        if(users.existsByUsuario(req.getUsuario())) return ApiResponse.fail("Usuario ya existe");
        User u = User.builder()
                .nombre(req.getNombre())
                .usuario(req.getUsuario())
                .email(req.getEmail())
                .telefono(req.getTelefono())
                .passwordHash(encoder.encode(req.getPassword()))
                .role(Role.USER)
                .build();
        users.save(u);

        String token = jwt.generate(u.getUsuario(), Map.of("role", u.getRole().name()));
        var me = UserMe.builder().id(u.getId()).nombre(u.getNombre()).rut(u.getUsuario()).email(u.getEmail()).build();
        return ApiResponse.ok(AuthResponse.builder().token(token).user(me).build(), "Registro exitoso");
    }

    public ApiResponse<AuthResponse> login(LoginRequest req){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsuario(), req.getPassword()));
        User u = users.findByUsuario(req.getUsuario()).orElseThrow();
        String token = jwt.generate(u.getUsuario(), Map.of("role", u.getRole().name()));
        var me = UserMe.builder().id(u.getId()).nombre(u.getNombre()).rut(u.getUsuario()).email(u.getEmail()).build();
        return ApiResponse.ok(AuthResponse.builder().token(token).user(me).build(), "Login OK");
    }

    public ApiResponse<UserMe> me(String usuario){
        User u = users.findByUsuario(usuario).orElseThrow();
        return ApiResponse.ok(UserMe.builder().id(u.getId()).nombre(u.getNombre()).rut(u.getUsuario()).email(u.getEmail()).build());
    }
}
