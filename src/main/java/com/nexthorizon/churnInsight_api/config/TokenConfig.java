package com.nexthorizon.churnInsight_api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nexthorizon.churnInsight_api.entity.User;
import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class TokenConfig {

  private final String secret;
  private final long expirationSeconds;

  public TokenConfig(
      @Value("${security.jwt.secret}") String secret,
      @Value("${security.jwt.expiration-seconds}") long expirationSeconds) {
    this.secret = secret;
    this.expirationSeconds = expirationSeconds;
  }

  public String generateToken(User user) {

    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withSubject(user.getEmail())
        .withClaim("userId", String.valueOf(user.getId()))
        .withClaim(
            "roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
        .withExpiresAt(Instant.now().plusSeconds(expirationSeconds))
        .withIssuedAt(Instant.now())
        .sign(algorithm);
  }

  public Optional<JWTUserData> validateToken(String token) {

    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      DecodedJWT decode = JWT.require(algorithm).build().verify(token);

      return Optional.of(
          JWTUserData.builder()
              .userID(decode.getClaim("userId").asLong())
              .email(decode.getSubject())
              .build());
    } catch (JWTVerificationException ex) {
      return Optional.empty();
    }
  }
}
