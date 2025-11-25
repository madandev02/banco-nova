package com.banconova.domain;

import com.banconova.domain.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="accounts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType tipo;

    @Column(nullable=false, unique=true)
    private String numero;

    @Enumerated(EnumType.STRING)
    private Currency moneda = Currency.CLP;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal saldo = BigDecimal.ZERO;

    @ManyToOne(optional=false)
    private User user;

    @OneToMany(mappedBy="account", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Movement> movimientos = new ArrayList<>();
}
