
package com.banconova.controller;

import com.banconova.dto.CardDto;
import com.banconova.service.CardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@Tag(name = "Tarjetas", description = "Tarjetas débito")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> list() {
        return ResponseEntity.ok(cardService.getMyCards());
    }

    @PostMapping("/debit")
    public ResponseEntity<CardDto> createDebitCard() {
        return ResponseEntity.ok(cardService.createDebitCard());
    }
}
