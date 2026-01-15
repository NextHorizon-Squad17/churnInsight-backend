package com.nexthorizon.churnInsight_api.controller;

import com.nexthorizon.churnInsight_api.dto.LoginRequest;
import com.nexthorizon.churnInsight_api.dto.LoginResponse;
import com.nexthorizon.churnInsight_api.dto.RegisterUserRequest;
import com.nexthorizon.churnInsight_api.dto.RegisterUserResponse;
import com.nexthorizon.churnInsight_api.service.UserAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserAuthService userAuthService;

  public AuthController(UserAuthService userAuthService) {
    this.userAuthService = userAuthService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    return ResponseEntity.ok(userAuthService.login(request));
  }

  @PostMapping("/register")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<RegisterUserResponse> register(
      @Valid @RequestBody RegisterUserRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userAuthService.register(request));
  }
}
