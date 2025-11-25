
package com.banconova.service;

import com.banconova.domain.entity.Investment;
import com.banconova.domain.entity.User;
import com.banconova.domain.enums.InvestmentType;
import com.banconova.dto.InvestmentDto;
import com.banconova.repository.InvestmentRepository;
import com.banconova.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final UserRepository userRepository;

    public InvestmentService(InvestmentRepository investmentRepository, UserRepository userRepository) {
        this.investmentRepository = investmentRepository;
        this.userRepository = userRepository;
    }

    public List<InvestmentDto> getMyInvestments() {
        User owner = getCurrentUser();
        return investmentRepository.findByOwner(owner).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public InvestmentDto createInvestment(InvestmentType type, BigDecimal amount) {
        User owner = getCurrentUser();
        BigDecimal estimatedReturn = amount.multiply(BigDecimal.valueOf(1 + randomRate()));

        Investment inv = Investment.builder()
                .owner(owner)
                .type(type)
                .amount(amount)
                .estimatedReturn(estimatedReturn)
                .createdAt(OffsetDateTime.now())
                .build();
        investmentRepository.save(inv);
        return toDto(inv);
    }

    private double randomRate() {
        return 0.02 + new Random().nextDouble() * 0.08;
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
    }

    private InvestmentDto toDto(Investment inv) {
        return InvestmentDto.builder()
                .id(inv.getId())
                .type(inv.getType())
                .amount(inv.getAmount())
                .estimatedReturn(inv.getEstimatedReturn())
                .createdAt(inv.getCreatedAt())
                .build();
    }
}
