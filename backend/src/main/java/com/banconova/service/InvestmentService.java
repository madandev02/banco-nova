
package com.banconova.service;

import com.banconova.domain.entity.Investment;
import com.banconova.domain.entity.User;
import com.banconova.domain.enums.InvestmentType;
import com.banconova.dto.InvestmentDto;
import com.banconova.repository.InvestmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public List<InvestmentDto> list(User user) {
        return investmentRepository.findByOwner(user).stream()
                .map(inv -> InvestmentDto.builder()
                        .id(inv.getId())
                        .type(inv.getType().name())
                        .amount(inv.getAmount())
                        .estimatedReturn(inv.getEstimatedReturn())
                        .build())
                .collect(Collectors.toList());
    }

    public InvestmentDto create(User user, InvestmentDto dto) {
        Investment inv = Investment.builder()
                .owner(user)
                .type(InvestmentType.valueOf(dto.getType()))
                .amount(dto.getAmount())
                .estimatedReturn(dto.getEstimatedReturn() != null ? dto.getEstimatedReturn() : dto.getAmount().multiply(new BigDecimal("0.05")))
                .createdAt(OffsetDateTime.now())
                .build();
        investmentRepository.save(inv);

        dto.setId(inv.getId());
        dto.setEstimatedReturn(inv.getEstimatedReturn());
        return dto;
    }
}
