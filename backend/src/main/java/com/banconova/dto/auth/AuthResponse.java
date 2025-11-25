package com.banconova.dto.auth;
import lombok.*;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class AuthResponse {
    private String token;
    private UserMe user;
}
