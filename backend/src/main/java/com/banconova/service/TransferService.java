package com.banconova.service;

import com.banconova.common.ApiResponse;
import com.banconova.domain.*;
import com.banconova.domain.enums.*;
import com.banconova.dto.transfer.TransferRequest;
import com.banconova.repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final UserRepository users;
    private final AccountRepository accounts;
    private final MovementRepository movements;
    private final TransferRepository transfers;

    @Transactional
    public ApiResponse<Transfer> transfer(String usuario, TransferRequest req){
        User u = users.findByUsuario(usuario).orElseThrow();
        Account origen = accounts.findByIdAndUser(req.getCuentaOrigenId(), u).orElseThrow();

        if(origen.getSaldo().compareTo(req.getMonto())<0){
            return ApiResponse.fail("Saldo insuficiente");
        }

        origen.setSaldo(origen.getSaldo().subtract(req.getMonto()));
        accounts.save(origen);

        Movement m = Movement.builder()
                .account(origen)
                .descripcion("Transferencia a "+req.getNombre()+" ("+req.getBancoDestino()+")")
                .monto(req.getMonto())
                .tipo(MovementType.CARGO)
                .estado(MovementStatus.OK)
                .build();
        movements.save(m);

        Transfer t = Transfer.builder()
                .user(u)
                .cuentaOrigen(origen)
                .bancoDestino(req.getBancoDestino())
                .rutDestino(req.getRut())
                .nombreDestino(req.getNombre())
                .tipoCuentaDestino(req.getTipoCuenta())
                .numeroCuentaDestino(req.getNumeroCuenta())
                .monto(req.getMonto())
                .mensaje(req.getMensaje())
                .build();
        transfers.save(t);

        return ApiResponse.ok(t, "Transferencia realizada");
    }

    public Object latest(String usuario){
        User u = users.findByUsuario(usuario).orElseThrow();
        return transfers.findTop10ByUserOrderByFechaDesc(u);
    }
}
