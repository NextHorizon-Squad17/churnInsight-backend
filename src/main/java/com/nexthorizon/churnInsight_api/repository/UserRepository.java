package com.nexthorizon.churnInsight_api.repository;

import com.nexthorizon.churnInsight_api.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<UserDetails> findUserByEmail(String username);

  boolean existsByIsAdminTrue();
}
