package com.finance.jwt.security.filter;

import com.finance.jwt.resolver.AccessTokenResolver;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class AccessTokenFilter extends OncePerRequestFilter {
  private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
  private final AuthenticationManager authenticationManager;
  private final AccessTokenResolver accessTokenResolver;

  public AccessTokenFilter(AuthenticationManager authenticationManager, AccessTokenResolver accessTokenResolver) {
    this.authenticationManager = authenticationManager;
    this.accessTokenResolver = accessTokenResolver;
  }

  @Override
  public final void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {
    try {
      accessTokenResolver.resolve(request).ifPresent(token -> {
        Authentication authenticate = authenticationManager.authenticate(token);
        securityContextHolderStrategy.getContext().setAuthentication(authenticate);
      });
    } catch (JwtException | IllegalArgumentException ex) {
      log.error(ex.getMessage());
    }
    filterChain.doFilter(request, response);
  }
}
