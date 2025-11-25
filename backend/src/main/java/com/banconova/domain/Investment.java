package com.banconova.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name="investments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Investment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    private String tipo; // DAP, Fondo...

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal monto;

    @Column(name="rentabilidad_anual")
    private double rentabilidadAnual;
    private String vencimiento;
}
