
package com.banconova.service;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.User;
import com.banconova.dto.AccountDto;
import com.banconova.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDto> getAccountsForUser(User user) {
        return accountRepository.findByOwner(user).stream()
                .map(a -> AccountDto.builder()
                        .id(a.getId())
                        .accountNumber(a.getAccountNumber())
                        .type(a.getType().name())
                        .balance(a.getBalance())
                        .currency(a.getCurrency())
                        .build())
                .collect(Collectors.toList());
    }
}
