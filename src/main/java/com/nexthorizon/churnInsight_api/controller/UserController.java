package com.nexthorizon.churnInsight_api.controller;

import com.nexthorizon.churnInsight_api.dto.UpdateUserRequest;
import com.nexthorizon.churnInsight_api.service.UserAuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserAuthService userAuthService;

    public UserController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PutMapping("/me")
    public ResponseEntity<Void> updateMyProfile(
            @RequestBody @Valid UpdateUserRequest request,
            @AuthenticationPrincipal UserDetails currentUser // Injeta o usuário do Token
    ) {
        // currentUser.getUsername() geralmente retorna o e-mail ou login
        userAuthService.updateUser(currentUser.getUsername(), request);
        return ResponseEntity.noContent().build();
    }
}
