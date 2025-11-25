
package com.banconova.controller;

import com.banconova.domain.entity.User;
import com.banconova.dto.InvestmentDto;
import com.banconova.repository.UserRepository;
import com.banconova.service.InvestmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
@Tag(name = "Investments", description = "Inversiones")
public class InvestmentController {

    private final InvestmentService investmentService;
    private final UserRepository userRepository;

    public InvestmentController(InvestmentService investmentService, UserRepository userRepository) {
        this.investmentService = investmentService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<InvestmentDto>> list(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(investmentService.list(user));
    }

    @PostMapping
    public ResponseEntity<InvestmentDto> create(@AuthenticationPrincipal UserDetails userDetails,
                                                @RequestBody InvestmentDto dto) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(investmentService.create(user, dto));
    }
}
