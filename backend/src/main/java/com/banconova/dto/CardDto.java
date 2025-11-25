
package com.banconova.dto;

import com.banconova.domain.enums.CardType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private Long id;
    private CardType type;
    private String maskedNumber;
    private LocalDate expirationDate;
}
