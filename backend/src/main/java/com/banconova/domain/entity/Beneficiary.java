
package com.banconova.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beneficiaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 20)
    private String rut;

    @Column(nullable = false)
    private String bank;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private String accountNumber;
}
