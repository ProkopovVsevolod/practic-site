package com.finance.jwt.security.authentication;

import com.finance.jwt.domain.OpenJwtToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider {
  private static class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg) {
      super(msg);
    }
  }

  @Override
  public Authentication authenticate(Authentication authentication) {
    OpenJwtToken token = (OpenJwtToken) authentication;
    if (!token.isValid()) {
      throw new JwtAuthenticationException("Invalid token:" + token);
    }
    token.setAuthenticated(true);
    return token;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (OpenJwtToken.class.isAssignableFrom(authentication));
  }
}
