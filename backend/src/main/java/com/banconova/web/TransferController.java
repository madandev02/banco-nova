package com.banconova.web;

import com.banconova.common.ApiResponse;
import com.banconova.domain.Transfer;
import com.banconova.dto.transfer.TransferRequest;
import com.banconova.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transferencias")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transfers;

    @PostMapping
    public ApiResponse<Transfer> create(Authentication auth, @Valid @RequestBody TransferRequest req){
        return transfers.transfer(auth.getName(), req);
    }

    @GetMapping("/ultimas")
    public ApiResponse<Object> latest(Authentication auth){
        return ApiResponse.ok(transfers.latest(auth.getName()));
    }
}
