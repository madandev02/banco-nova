
package com.banconova.service;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.Transfer;
import com.banconova.domain.enums.MovementType;
import com.banconova.dto.TransferDtos;
import com.banconova.repository.AccountRepository;
import com.banconova.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@Transactional
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    private final MovementService movementService;

    public TransferService(AccountRepository accountRepository,
                           TransferRepository transferRepository,
                           MovementService movementService) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
        this.movementService = movementService;
    }

    public void createTransfer(TransferDtos.CreateTransferRequest request) {
        Account from = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no encontrada"));

        if (from.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        Account to = null;
        if (request.getToAccountId() != null) {
            to = accountRepository.findById(request.getToAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("Cuenta destino no encontrada"));
        }

        Transfer transfer = Transfer.builder()
                .fromAccount(from)
                .toAccount(to)
                .destinationBank(request.getDestinationBank())
                .destinationAccountNumber(request.getDestinationAccountNumber())
                .amount(request.getAmount())
                .createdAt(OffsetDateTime.now())
                .description(request.getDescription())
                .build();

        transferRepository.save(transfer);

        // update balances
        from.setBalance(from.getBalance().subtract(request.getAmount()));
        accountRepository.save(from);
        movementService.registerMovement(from, MovementType.TRANSFERENCIA_ENVIADA,
                request.getAmount().negate(), request.getDescription());

        if (to != null) {
            to.setBalance(to.getBalance().add(request.getAmount()));
            accountRepository.save(to);
            movementService.registerMovement(to, MovementType.TRANSFERENCIA_RECIBIDA,
                    request.getAmount(), request.getDescription());
        }
    }
}
