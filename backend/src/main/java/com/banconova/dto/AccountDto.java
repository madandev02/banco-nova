
package com.banconova.dto;

import com.banconova.domain.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long id;
    private String accountNumber;
    private AccountType type;
    private BigDecimal balance;
    private String currency;
}
