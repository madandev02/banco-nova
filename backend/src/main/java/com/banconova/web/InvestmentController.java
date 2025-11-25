package com.banconova.web;

import com.banconova.common.ApiResponse;
import com.banconova.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inversiones")
@RequiredArgsConstructor
public class InvestmentController {
    private final InvestmentService investments;

    @GetMapping
    public ApiResponse<Object> list(Authentication auth){
        return ApiResponse.ok(investments.list(auth.getName()));
    }
}
