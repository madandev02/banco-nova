
package com.banconova.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDto {
    private BigDecimal totalBalance;
    private BigDecimal totalInvested;
    private List<MovementDto> latestMovements;
}
