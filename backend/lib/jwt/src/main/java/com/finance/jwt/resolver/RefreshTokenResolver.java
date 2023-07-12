package com.finance.jwt.resolver;

import com.finance.jwt.domain.token.TokenMetadata;
import com.finance.jwt.domain.OpenRefreshToken;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class RefreshTokenResolver implements TokenResolver {

  @Override
  public Optional<OpenRefreshToken> resolve(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null) return Optional.empty();

    for (Cookie cookie : cookies) {
      if (cookie.getName().equals(TokenMetadata.REFRESH.getHeader())) {
        return Optional.of(new OpenRefreshToken(cookie.getValue()));
      }
    }
    return Optional.empty();
  }
}
