package com.banconova.domain;

import com.banconova.domain.enums.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Table(name="cards")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    @Enumerated(EnumType.STRING)
    private CardType tipo;

    @Column(nullable=false, unique=true)
    @Column(name="numero_masked", nullable=false, unique=true)
    private String numeroMasked; // **** 4455

    private BigDecimal cupo = BigDecimal.ZERO;
    private BigDecimal utilizado = BigDecimal.ZERO;

    private boolean activa = true;
}
