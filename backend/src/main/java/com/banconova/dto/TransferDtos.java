
package com.banconova.dto;

import lombok.*;
import java.math.BigDecimal;

public class TransferDtos {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateTransferRequest {
        private String fromAccountNumber;
        private String toAccountNumber;
        private String destinationBank;
        private String destinationAccountNumber;
        private BigDecimal amount;
        private String description;
    }
}
