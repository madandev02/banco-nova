
package com.banconova.service;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.User;
import com.banconova.dto.DashboardDto;
import com.banconova.dto.MovementDto;
import com.banconova.repository.AccountRepository;
import com.banconova.repository.InvestmentRepository;
import com.banconova.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    private final AccountRepository accountRepository;
    private final InvestmentRepository investmentRepository;
    private final MovementService movementService;
    private final UserRepository userRepository;

    public DashboardService(AccountRepository accountRepository,
                            InvestmentRepository investmentRepository,
                            MovementService movementService,
                            UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.investmentRepository = investmentRepository;
        this.movementService = movementService;
        this.userRepository = userRepository;
    }

    public DashboardDto getDashboard() {
        User user = getCurrentUser();
        List<Account> accounts = accountRepository.findByOwner(user);

        BigDecimal totalBalance = accounts.stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalInvested = investmentRepository.findByOwner(user).stream()
                .map(inv -> inv.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<MovementDto> latest = accounts.stream()
                .flatMap(a -> movementService.getLatestMovements(a.getId()).stream())
                .limit(10)
                .toList();

        return DashboardDto.builder()
                .totalBalance(totalBalance)
                .totalInvested(totalInvested)
                .latestMovements(latest)
                .build();
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
    }
}
