package com.nexthorizon.churnInsight_api.service;

import com.nexthorizon.churnInsight_api.config.TokenConfig;
import com.nexthorizon.churnInsight_api.dto.LoginRequest;
import com.nexthorizon.churnInsight_api.dto.LoginResponse;
import com.nexthorizon.churnInsight_api.dto.RegisterUserRequest;
import com.nexthorizon.churnInsight_api.dto.RegisterUserResponse;
import com.nexthorizon.churnInsight_api.entity.User;
import com.nexthorizon.churnInsight_api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final TokenConfig tokenConfig;

  public UserAuthService(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      AuthenticationManager authenticationManager,
      TokenConfig tokenConfig) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.tokenConfig = tokenConfig;
  }

  public LoginResponse login(LoginRequest request) {

    UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(request.email(), request.password());

    Authentication authentication = authenticationManager.authenticate(authToken);

    User user = (User) authentication.getPrincipal();
    String token = tokenConfig.generateToken(user);

    return new LoginResponse(token);
  }

  public RegisterUserResponse register(RegisterUserRequest request) {
    User newUser = new User();
    newUser.setName(request.name());
    newUser.setEmail(request.email());
    newUser.setPassword(passwordEncoder.encode(request.password()));

    userRepository.save(newUser);

    return new RegisterUserResponse(newUser.getName(), newUser.getEmail());
  }
}
