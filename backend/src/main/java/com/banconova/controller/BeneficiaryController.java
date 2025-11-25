
package com.banconova.controller;

import com.banconova.domain.entity.User;
import com.banconova.dto.BeneficiaryDto;
import com.banconova.repository.UserRepository;
import com.banconova.service.BeneficiaryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
@Tag(name = "Beneficiaries", description = "Beneficiarios")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;
    private final UserRepository userRepository;

    public BeneficiaryController(BeneficiaryService beneficiaryService, UserRepository userRepository) {
        this.beneficiaryService = beneficiaryService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<BeneficiaryDto>> list(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(beneficiaryService.list(user));
    }

    @PostMapping
    public ResponseEntity<BeneficiaryDto> create(@AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestBody BeneficiaryDto dto) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(beneficiaryService.create(user, dto));
    }
}
