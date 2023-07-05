package com.finance.jwt.security.refresh;

import com.finance.jwt.domain.OpenRefreshToken;
import com.finance.jwt.domain.TokenPair;
import com.finance.jwt.resolver.RequestMetadata;
import com.finance.jwt.security.authentication.UsernamePasswordAuthentication;

public interface RefreshSessionService {
  TokenPair create(RequestMetadata requestMetadata, UsernamePasswordAuthentication authentication);
  TokenPair refresh(OpenRefreshToken refreshToken);
  void delete(OpenRefreshToken refreshToken);
}
