package com.nexthorizon.churnInsight_api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nexthorizon.churnInsight_api.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    // TODO: mover esta variavel para uma variavel de ambiente
    private final String secret = "secret";

    public String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", String.valueOf(user.getId()))
                .withClaim("roles",
                        user.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList())
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)
                    .build().verify(token);

            return Optional.of(JWTUserData.builder()
                            .userID(decode.getClaim("userId").asLong())
                            .email(decode.getSubject())
                            .build());
        }
        catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }

}
