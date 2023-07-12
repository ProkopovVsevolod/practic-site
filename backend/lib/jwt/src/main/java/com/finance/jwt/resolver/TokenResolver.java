package com.finance.jwt.resolver;

import com.finance.jwt.domain.OpenJwtToken;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface TokenResolver {
  Optional<? extends OpenJwtToken> resolve(HttpServletRequest request);
}
