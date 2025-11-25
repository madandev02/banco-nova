
package com.banconova.service;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.Role;
import com.banconova.domain.entity.User;
import com.banconova.domain.enums.AccountType;
import com.banconova.dto.AuthDtos;
import com.banconova.repository.AccountRepository;
import com.banconova.repository.RoleRepository;
import com.banconova.repository.UserRepository;
import com.banconova.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthDtos.AuthResponse register(AuthDtos.RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("USER").build()));

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .rut(request.getRut())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(userRole))
                .build();

        userRepository.save(user);

        Account account = Account.builder()
                .accountNumber(generateAccountNumber(user.getId()))
                .type(AccountType.VISTA)
                .balance(BigDecimal.ZERO)
                .currency("CLP")
                .owner(user)
                .build();

        accountRepository.save(account);

        String access = jwtService.generateToken(user.getEmail());
        String refresh = jwtService.generateRefreshToken(user.getEmail());

        return AuthDtos.AuthResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .tokenType("Bearer")
                .build();
    }

    @Override
    public AuthDtos.AuthResponse login(AuthDtos.LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String username = auth.getName();
        String access = jwtService.generateToken(username);
        String refresh = jwtService.generateRefreshToken(username);

        return AuthDtos.AuthResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .tokenType("Bearer")
                .build();
    }

    private String generateAccountNumber(Long userId) {
        return "10" + String.format("%08d", userId);
    }
}
