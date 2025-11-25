
package com.banconova.service;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.User;
import com.banconova.dto.AccountDto;
import com.banconova.repository.AccountRepository;
import com.banconova.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<AccountDto> getMyAccounts() {
        User user = getCurrentUser();
        return accountRepository.findByOwner(user).stream()
                .map(a -> AccountDto.builder()
                        .id(a.getId())
                        .accountNumber(a.getAccountNumber())
                        .type(a.getType())
                        .balance(a.getBalance())
                        .currency(a.getCurrency())
                        .build())
                .collect(Collectors.toList());
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
    }
}
