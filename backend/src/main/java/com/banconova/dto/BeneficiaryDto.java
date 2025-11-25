
package com.banconova.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficiaryDto {
    private Long id;
    private String name;
    private String rut;
    private String bank;
    private String accountType;
    private String accountNumber;
}
