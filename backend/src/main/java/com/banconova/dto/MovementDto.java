
package com.banconova.dto;

import com.banconova.domain.enums.MovementType;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementDto {
    private Long id;
    private Long accountId;
    private MovementType type;
    private BigDecimal amount;
    private OffsetDateTime createdAt;
    private String description;
}
