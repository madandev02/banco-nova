package com.banconova.service;

import com.banconova.repo.InvestmentRepository;
import com.banconova.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestmentService {
    private final InvestmentRepository investments;
    private final UserRepository users;

    public Object list(String usuario){
        var u = users.findByUsuario(usuario).orElseThrow();
        return investments.findByUser(u);
    }
}
