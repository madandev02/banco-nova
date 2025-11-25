
package com.banconova.dto;

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
    private String type;
    private BigDecimal amount;
    private OffsetDateTime createdAt;
    private String description;
}
