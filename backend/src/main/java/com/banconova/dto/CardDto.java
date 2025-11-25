
package com.banconova.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private Long id;
    private String maskedNumber;
    private String type;
    private LocalDate expirationDate;
}
