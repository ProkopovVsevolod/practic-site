package com.finance.jwt.security.authentication;

import com.finance.jwt.domain.OpenRefreshToken;
import com.finance.jwt.domain.TokenPair;
import com.finance.jwt.resolver.RequestMetadata;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticateService {
  TokenPair login(UsernamePasswordAuthenticationToken authentication, RequestMetadata requestMetadata);
  TokenPair refresh(OpenRefreshToken refreshToken);
  void logout(OpenRefreshToken refreshToken);
}
