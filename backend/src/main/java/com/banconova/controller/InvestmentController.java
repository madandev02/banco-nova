
package com.banconova.controller;

import com.banconova.domain.enums.InvestmentType;
import com.banconova.dto.InvestmentDto;
import com.banconova.service.InvestmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/investments")
@Tag(name = "Inversiones", description = "Gestión de inversiones")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GetMapping
    public ResponseEntity<List<InvestmentDto>> list() {
        return ResponseEntity.ok(investmentService.getMyInvestments());
    }

    @PostMapping
    public ResponseEntity<InvestmentDto> create(@RequestParam InvestmentType type,
                                                @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(investmentService.createInvestment(type, amount));
    }
}
