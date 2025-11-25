package com.banconova.web;

import com.banconova.common.ApiResponse;
import com.banconova.dto.account.AccountDto;
import com.banconova.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accounts;

    @GetMapping
    public ApiResponse<List<AccountDto>> list(Authentication auth){
        return ApiResponse.ok(accounts.listForUser(auth.getName()));
    }
}
