package com.banconova.web;

import com.banconova.common.ApiResponse;
import com.banconova.domain.Beneficiary;
import com.banconova.service.BeneficiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beneficiarios")
@RequiredArgsConstructor
public class BeneficiaryController {
    private final BeneficiaryService beneficiaries;

    @GetMapping
    public ApiResponse<Object> list(Authentication auth){
        return ApiResponse.ok(beneficiaries.list(auth.getName()));
    }

    @PostMapping
    public ApiResponse<Beneficiary> create(Authentication auth, @Valid @RequestBody Beneficiary b){
        return beneficiaries.create(auth.getName(), b);
    }

    @PatchMapping("/{id}/favorito")
    public ApiResponse<Beneficiary> toggle(Authentication auth, @PathVariable Long id){
        return beneficiaries.toggleFav(auth.getName(), id);
    }
}
