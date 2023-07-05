package com.finance.jwt.security.filter;

import com.finance.jwt.resolver.AccessTokenResolver;
import com.finance.logger.aop.stereotype.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Filter
public class AccessTokenFilter extends OncePerRequestFilter {
  private final SecurityContext context;
  private final AuthenticationManager authenticationManager;
  private final AccessTokenResolver accessTokenResolver;

  public AccessTokenFilter(AuthenticationManager authenticationManager, AccessTokenResolver accessTokenResolver) {
    this.context = SecurityContextHolder.getContextHolderStrategy().getContext();
    this.authenticationManager = authenticationManager;
    this.accessTokenResolver = accessTokenResolver;
  }

  @Override
  public final void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {
    accessTokenResolver.resolve(request).ifPresent(token -> {
      Authentication authenticate = authenticationManager.authenticate(token);
      context.setAuthentication(authenticate);
    });
    filterChain.doFilter(request, response);
  }
}
