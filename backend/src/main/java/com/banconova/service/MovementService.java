
package com.banconova.service;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.Movement;
import com.banconova.domain.enums.MovementType;
import com.banconova.dto.MovementDto;
import com.banconova.repository.AccountRepository;
import com.banconova.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    public MovementService(MovementRepository movementRepository, AccountRepository accountRepository) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
    }

    public List<MovementDto> getLatestMovements(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        return movementRepository.findTop10ByAccountOrderByCreatedAtDesc(account)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void registerMovement(Account account, MovementType type, BigDecimal amount, String description) {
        Movement movement = Movement.builder()
                .account(account)
                .type(type)
                .amount(amount)
                .createdAt(OffsetDateTime.now())
                .description(description)
                .build();

        movementRepository.save(movement);
    }

    private MovementDto toDto(Movement m) {
        return MovementDto.builder()
                .id(m.getId())
                .accountId(m.getAccount().getId())
                .type(m.getType())
                .amount(m.getAmount())
                .createdAt(m.getCreatedAt())
                .description(m.getDescription())
                .build();
    }
}
