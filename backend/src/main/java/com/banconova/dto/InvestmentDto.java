
package com.banconova.dto;

import com.banconova.domain.enums.InvestmentType;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestmentDto {
    private Long id;
    private InvestmentType type;
    private BigDecimal amount;
    private BigDecimal estimatedReturn;
    private OffsetDateTime createdAt;
}
