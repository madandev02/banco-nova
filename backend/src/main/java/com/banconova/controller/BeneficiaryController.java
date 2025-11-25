
package com.banconova.controller;

import com.banconova.dto.BeneficiaryDto;
import com.banconova.service.BeneficiaryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
@Tag(name = "Beneficiarios", description = "Gestión de beneficiarios")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @GetMapping
    public ResponseEntity<List<BeneficiaryDto>> list() {
        return ResponseEntity.ok(beneficiaryService.getMyBeneficiaries());
    }

    @PostMapping
    public ResponseEntity<BeneficiaryDto> create(@RequestBody BeneficiaryDto dto) {
        return ResponseEntity.ok(beneficiaryService.create(dto));
    }
}
