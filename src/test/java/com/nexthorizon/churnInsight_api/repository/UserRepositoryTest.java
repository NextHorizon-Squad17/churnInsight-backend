package com.nexthorizon.churnInsight_api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexthorizon.churnInsight_api.dto.RegisterUserRequest;
import com.nexthorizon.churnInsight_api.entity.User;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

  @Autowired UserRepository userRepository;

  @Autowired EntityManager entityManager;

  @Test
  @DisplayName("Should get User successfully from DB")
  void findUserByEmailCase1() {
    String email = "josericardo@gmail.com";
    RegisterUserRequest data = new RegisterUserRequest("José Ricardo", email, "rjose123");
    this.createUser(data);

    Optional<UserDetails> result = this.userRepository.findUserByEmail(email);

    assertThat(result.isPresent()).isTrue();
  }

  @Test
  @DisplayName("Should not get User from DB when user not exists")
  void findUserByEmailCase2() {
    String email = "josericardo@gmail.com";

    Optional<UserDetails> result = this.userRepository.findUserByEmail(email);

    assertThat(result.isEmpty()).isTrue();
  }

  private User createUser(RegisterUserRequest data) {
    User newUser = new User(data);
    this.entityManager.persist(newUser);
    return newUser;
  }
}
