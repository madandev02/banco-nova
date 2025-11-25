package com.banconova.dto.account;
import lombok.*;
import java.math.BigDecimal;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class AccountDto {
    private Long id;
    private String tipo;
    private String numero;
    private String moneda;
    private BigDecimal saldo;
}
