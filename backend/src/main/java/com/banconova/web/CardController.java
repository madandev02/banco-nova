package com.banconova.web;

import com.banconova.common.ApiResponse;
import com.banconova.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarjetas")
@RequiredArgsConstructor
public class CardController {
    private final CardService cards;

    @GetMapping
    public ApiResponse<Object> list(Authentication auth){
        return ApiResponse.ok(cards.list(auth.getName()));
    }
}
