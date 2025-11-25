package com.banconova.service;

import com.banconova.dto.account.AccountDto;
import com.banconova.repo.AccountRepository;
import com.banconova.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accounts;
    private final UserRepository users;

    public List<AccountDto> listForUser(String usuario){
        var u = users.findByUsuario(usuario).orElseThrow();
        return accounts.findByUser(u).stream()
                .map(a-> AccountDto.builder()
                        .id(a.getId())
                        .tipo(a.getTipo().name())
                        .numero(a.getNumero())
                        .moneda(a.getMoneda().name())
                        .saldo(a.getSaldo())
                        .build())
                .toList();
    }
}
