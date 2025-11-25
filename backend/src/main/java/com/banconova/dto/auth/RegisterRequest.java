package com.banconova.dto.auth;
import jakarta.validation.constraints.*;
import lombok.Getter; import lombok.Setter;
@Getter @Setter
public class RegisterRequest {
    @NotBlank private String nombre;
    @NotBlank private String usuario;
    @Email @NotBlank private String email;
    private String telefono;
    @Size(min=6) private String password;
}
