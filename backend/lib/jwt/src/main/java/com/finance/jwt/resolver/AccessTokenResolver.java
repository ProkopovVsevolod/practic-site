package com.finance.jwt.resolver;

import com.finance.jwt.config.token.TokenMetadata;
import com.finance.jwt.domain.OpenAccessToken;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class AccessTokenResolver implements TokenResolver {

  @Override
  public Optional<OpenAccessToken> resolve(HttpServletRequest request) {
    String bearerToken = request.getHeader(TokenMetadata.ACCESS.getHeader());
    String bearerPrefix = "Bearer ";

    return bearerToken != null && bearerToken.startsWith(bearerPrefix)
      ? Optional.of(new OpenAccessToken(bearerToken.substring(bearerPrefix.length())))
      : Optional.empty();
  }
}
