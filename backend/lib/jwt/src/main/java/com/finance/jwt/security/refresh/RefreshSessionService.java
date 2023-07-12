package com.finance.jwt.security.refresh;

import com.finance.jwt.domain.OpenRefreshToken;
import com.finance.jwt.resolver.RequestMetadata;

public interface RefreshSessionService {
  void create(RequestMetadata requestMetadata, Long userId, OpenRefreshToken refreshToken);
  void refresh(String oldToken, String newToken);
  void delete(OpenRefreshToken refreshToken);
}
