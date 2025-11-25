package com.banconova.service;

import com.banconova.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovementService {
    private final AccountRepository accounts;
    private final UserRepository users;
    private final MovementRepository movements;

    public Object list(String usuario, Long cuentaId){
        var u = users.findByUsuario(usuario).orElseThrow();
        var acc = accounts.findByIdAndUser(cuentaId, u).orElseThrow();
        return movements.findByAccountOrderByFechaDesc(acc);
    }

    public Object latest(String usuario, Long cuentaId){
        var u = users.findByUsuario(usuario).orElseThrow();
        var acc = accounts.findByIdAndUser(cuentaId, u).orElseThrow();
        return movements.findTop10ByAccountOrderByFechaDesc(acc);
    }
}
