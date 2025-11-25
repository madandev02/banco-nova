package com.banconova.security;

import com.banconova.repo.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository users;
    public CustomUserDetailsService(UserRepository users){ this.users=users; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = users.findByUsuario(username)
                .orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getUsuario())
                .password(u.getPasswordHash())
                .roles(u.getRole().name())
                .build();
    }
}
