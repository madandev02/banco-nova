package com.banconova.dto.auth;
import lombok.*;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class UserMe {
    private Long id;
    private String nombre;
    private String rut;
    private String email;
}
