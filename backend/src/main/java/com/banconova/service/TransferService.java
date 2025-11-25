
package com.banconova.service;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.Movement;
import com.banconova.domain.entity.Transfer;
import com.banconova.domain.enums.MovementType;
import com.banconova.dto.TransferDtos;
import com.banconova.repository.AccountRepository;
import com.banconova.repository.MovementRepository;
import com.banconova.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;
    private final TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository,
                           MovementRepository movementRepository,
                           TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public void createTransfer(TransferDtos.CreateTransferRequest request) {
        Account from = accountRepository.findByAccountNumber(request.getFromAccountNumber())
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));

        if (from.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        Account to = null;
        boolean isInternal = request.getToAccountNumber() != null && !request.getToAccountNumber().isBlank();
        if (isInternal) {
            to = accountRepository.findByAccountNumber(request.getToAccountNumber())
                    .orElseThrow(() -> new RuntimeException("Cuenta destino interna no encontrada"));
        }

        BigDecimal amount = request.getAmount();
        from.setBalance(from.getBalance().subtract(amount));
        accountRepository.save(from);

        if (isInternal) {
            to.setBalance(to.getBalance().add(amount));
            accountRepository.save(to);
        }

        OffsetDateTime now = OffsetDateTime.now();

        Movement out = Movement.builder()
                .account(from)
                .type(MovementType.TRANSFERENCIA_ENVIADA)
                .amount(amount.negate())
                .createdAt(now)
                .description(request.getDescription())
                .build();
        movementRepository.save(out);

        if (isInternal) {
            Movement in = Movement.builder()
                    .account(to)
                    .type(MovementType.TRANSFERENCIA_RECIBIDA)
                    .amount(amount)
                    .createdAt(now)
                    .description(request.getDescription())
                    .build();
            movementRepository.save(in);
        }

        Transfer transfer = Transfer.builder()
                .fromAccount(from)
                .toAccount(to)
                .destinationBank(request.getDestinationBank())
                .destinationAccountNumber(request.getDestinationAccountNumber())
                .amount(amount)
                .createdAt(now)
                .description(request.getDescription())
                .build();
        transferRepository.save(transfer);
    }
}
