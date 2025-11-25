
package com.banconova.controller;

import com.banconova.domain.entity.Account;
import com.banconova.domain.entity.User;
import com.banconova.dto.MovementDto;
import com.banconova.repository.AccountRepository;
import com.banconova.repository.UserRepository;
import com.banconova.service.MovementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
@Tag(name = "Movements", description = "Movimientos bancarios")
public class MovementController {

    private final MovementService movementService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public MovementController(MovementService movementService,
                              UserRepository userRepository,
                              AccountRepository accountRepository) {
        this.movementService = movementService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<List<MovementDto>> list(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "10") int limit) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Account account = accountRepository.findByOwner(user).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario sin cuenta"));
        return ResponseEntity.ok(movementService.getLastMovements(account, limit));
    }
}
