package com.nexthorizon.churnInsight_api.bootstrap;

import com.nexthorizon.churnInsight_api.entity.User;
import com.nexthorizon.churnInsight_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminBootstrap implements CommandLineRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${bootstrap.admin.email:}")
  private String email;

  @Value("${bootstrap.admin.password:}")
  private String password;

  @Value("${bootstrap.admin.name:}")
  private String name;

  @Override
  public void run(String... args) {
    System.out.println(">>> AdminBootstrap executado <<<");

    if (userRepository.existsByIsAdminTrue()) {
      return;
    }

    if (email.isBlank() || password.isBlank() || name.isBlank()) {
      return;
    }

    User admin = new User();
    admin.setName(name);
    admin.setEmail(email);
    admin.setPassword(passwordEncoder.encode(password));
    admin.setAdmin(true);

    userRepository.save(admin);

    System.out.println("Admin bootstrap created");
  }
}
