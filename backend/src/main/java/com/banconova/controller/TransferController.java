
package com.banconova.controller;

import com.banconova.dto.TransferDtos;
import com.banconova.service.TransferService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
@Tag(name = "Transfers", description = "Transferencias bancarias")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TransferDtos.CreateTransferRequest request) {
        transferService.createTransfer(request);
        return ResponseEntity.ok().build();
    }
}
