
package com.banconova.controller;

import com.banconova.dto.UserProfileDto;
import com.banconova.service.UserProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@Tag(name = "Perfil", description = "Perfil del usuario autenticado")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<UserProfileDto> getProfile() {
        return ResponseEntity.ok(userProfileService.getProfile());
    }
}
