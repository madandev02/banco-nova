
package com.banconova.controller;

import com.banconova.dto.MovementDto;
import com.banconova.service.MovementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
@Tag(name = "Movimientos", description = "Movimientos bancarios")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<MovementDto>> getLatest(@PathVariable Long accountId) {
        return ResponseEntity.ok(movementService.getLatestMovements(accountId));
    }
}
