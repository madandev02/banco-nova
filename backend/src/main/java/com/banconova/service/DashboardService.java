
package com.banconova.service;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.User;
import com.banconova.dto.DashboardDto;
import com.banconova.repository.AccountRepository;
import com.banconova.repository.InvestmentRepository;
import com.banconova.repository.MovementRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    private final AccountRepository accountRepository;
    private final InvestmentRepository investmentRepository;
    private final MovementRepository movementRepository;

    public DashboardService(AccountRepository accountRepository,
                            InvestmentRepository investmentRepository,
                            MovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.investmentRepository = investmentRepository;
        this.movementRepository = movementRepository;
    }

    public DashboardDto getDashboard(User user) {
        List<Account> accounts = accountRepository.findByOwner(user);
        BigDecimal totalBalance = accounts.stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalInvested = investmentRepository.findByOwner(user).stream()
                .map(inv -> inv.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var firstAccount = accounts.stream().findFirst().orElse(null);

        List<String> lastMovements = List.of();
        if (firstAccount != null) {
            lastMovements = movementRepository
                    .findByAccountOrderByCreatedAtDesc(firstAccount, PageRequest.of(0, 5))
                    .map(m -> m.getType() + " " + m.getAmount())
                    .getContent();
        }

        return DashboardDto.builder()
                .totalBalance(totalBalance)
                .totalInvested(totalInvested)
                .lastMovements(lastMovements)
                .build();
    }
}
