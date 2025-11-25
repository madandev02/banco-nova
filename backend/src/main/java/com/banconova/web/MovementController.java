package com.banconova.web;

import com.banconova.common.ApiResponse;
import com.banconova.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovementController {
    private final MovementService movements;

    @GetMapping
    public ApiResponse<Object> list(Authentication auth, @RequestParam Long cuentaId){
        return ApiResponse.ok(movements.list(auth.getName(), cuentaId));
    }

    @GetMapping("/ultimos")
    public ApiResponse<Object> latest(Authentication auth, @RequestParam Long cuentaId){
        return ApiResponse.ok(movements.latest(auth.getName(), cuentaId));
    }
}
