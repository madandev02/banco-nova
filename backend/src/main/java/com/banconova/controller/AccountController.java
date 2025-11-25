
package com.banconova.controller;

import com.banconova.dto.AccountDto;
import com.banconova.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Cuentas", description = "Gestión de cuentas bancarias")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getMyAccounts() {
        return ResponseEntity.ok(accountService.getMyAccounts());
    }
}
