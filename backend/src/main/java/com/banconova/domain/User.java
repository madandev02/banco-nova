package com.banconova.domain;

import com.banconova.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity @Table(name="users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false, unique=true)
    private String usuario; // RUT o email login

    @Column(nullable=false)
    private String email;

    private String telefono;

    @Column(name="password_hash", nullable=false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Account> cuentas = new ArrayList<>();
}
