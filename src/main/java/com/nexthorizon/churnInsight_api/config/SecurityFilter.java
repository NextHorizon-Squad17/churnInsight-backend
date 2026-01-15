package com.nexthorizon.churnInsight_api.config;

import com.nexthorizon.churnInsight_api.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  private final TokenConfig tokenConfig;
  private final AuthService authService;

  public SecurityFilter(TokenConfig tokenConfig, AuthService authService) {
    this.tokenConfig = tokenConfig;
    this.authService = authService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if (Strings.isNotBlank(authHeader) && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring("Bearer ".length());

      Optional<JWTUserData> optUserData = tokenConfig.validateToken(token);

      if (optUserData.isPresent()
          && SecurityContextHolder.getContext().getAuthentication() == null) {

        JWTUserData userData = optUserData.get();

        UserDetails user = authService.loadUserByUsername(userData.email());

        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userData, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    filterChain.doFilter(request, response);
  }
}
