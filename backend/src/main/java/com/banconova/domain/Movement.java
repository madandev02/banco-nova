package com.banconova.domain;

import com.banconova.domain.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name="movements")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Movement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private Account account;

    private LocalDateTime fecha = LocalDateTime.now();

    @Column(nullable=false)
    private String descripcion;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    private MovementType tipo;

    @Enumerated(EnumType.STRING)
    private MovementStatus estado = MovementStatus.OK;
}
