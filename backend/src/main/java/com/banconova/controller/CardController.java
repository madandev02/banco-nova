
package com.banconova.controller;

import com.banconova.domain.entity.User;
import com.banconova.dto.CardDto;
import com.banconova.repository.UserRepository;
import com.banconova.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@Tag(name = "Cards", description = "Tarjetas de débito")
public class CardController {

    private final CardService cardService;
    private final UserRepository userRepository;

    public CardController(CardService cardService, UserRepository userRepository) {
        this.cardService = cardService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> list(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(cardService.list(user));
    }

    @PostMapping
    public ResponseEntity<CardDto> create(@AuthenticationPrincipal UserDetails userDetails,
                                          @RequestParam String last4) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(cardService.createDebitCard(user, last4));
    }
}
