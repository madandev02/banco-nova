
package com.banconova.service;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.Movement;
import com.banconova.dto.MovementDto;
import com.banconova.repository.MovementRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementService {

    private final MovementRepository movementRepository;

    public MovementService(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    public List<MovementDto> getLastMovements(Account account, int limit) {
        return movementRepository.findByAccountOrderByCreatedAtDesc(account, PageRequest.of(0, limit))
                .getContent()
                .stream()
                .map(m -> MovementDto.builder()
                        .id(m.getId())
                        .type(m.getType().name())
                        .amount(m.getAmount())
                        .createdAt(m.getCreatedAt())
                        .description(m.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
