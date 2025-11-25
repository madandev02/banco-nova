package com.banconova.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="beneficiaries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Beneficiary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    @Column(nullable=false)
    private String alias;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false)
    private String rut;

    @Column(nullable=false)
    private String banco;

    @Column(name="tipo_cuenta", nullable=false)
    private String tipoCuenta;

    @Column(name="numero_cuenta", nullable=false)
    private String numeroCuenta;

    private boolean favorito = false;
}
