package com.banconova.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name="transfers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transfer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="cuenta_origen_id")
    private Account cuentaOrigen;

    @ManyToOne(optional=false)
    private User user;

    @Column(name="banco_destino")
    private String bancoDestino;
    @Column(name="rut_destino")
    private String rutDestino;
    @Column(name="nombre_destino")
    private String nombreDestino;
    @Column(name="tipo_cuenta_destino")
    private String tipoCuentaDestino;
    @Column(name="numero_cuenta_destino")
    private String numeroCuentaDestino;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal monto;

    private String mensaje;

    private LocalDateTime fecha = LocalDateTime.now();
}
