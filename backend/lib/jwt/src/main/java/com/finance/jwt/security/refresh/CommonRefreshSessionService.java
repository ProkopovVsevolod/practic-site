package com.finance.jwt.security.refresh;

import com.finance.jwt.domain.OpenAccessToken;
import com.finance.jwt.domain.OpenRefreshToken;
import com.finance.jwt.domain.TokenPair;
import com.finance.jwt.resolver.RequestMetadata;
import com.finance.jwt.security.authentication.UsernamePasswordAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class CommonRefreshSessionService implements RefreshSessionService {
  private static final Long MAX_COUNT_OF_SESSIONS_BY_USER = 2L;
  private final RefreshSessionRepository repository;

  @Autowired
  public CommonRefreshSessionService(RefreshSessionRepository repository) {
    this.repository = repository;
  }

  @Override
  public TokenPair create(RequestMetadata requestMetadata,
                          UsernamePasswordAuthentication authentication) {
    Claims claims = new DefaultClaims(Map.of(
      "authorities", authentication.getAuthorities()
    ));
    claims.setSubject((String)authentication.getPrincipal());

    OpenAccessToken accessToken = OpenAccessToken.generate(claims);
    OpenRefreshToken refreshToken = OpenRefreshToken.generate(claims);

    RefreshSession refreshSession = new RefreshSession(
      authentication.getUserId(),
      requestMetadata.getRemoteAddress(),
      requestMetadata.getUserAgent(),
      refreshToken.getBody(),
      refreshToken.getInjectedClaims().getExpiration()
    );
    deleteExceed(refreshSession);
    repository.save(refreshSession);
    return new TokenPair(accessToken, refreshToken);
  }

  @Override
  public TokenPair refresh(OpenRefreshToken refreshToken) {
    String body = refreshToken.getBody();
    Date expiration = refreshToken.getInjectedClaims().getExpiration();
    Claims claims = refreshToken.getInjectedClaims();

    RefreshSession presentSession = repository.findByRefreshToken(body);
    OpenAccessToken newAccess = OpenAccessToken.generate(claims);
    OpenRefreshToken newRefresh = OpenRefreshToken.generate(claims, expiration);

    presentSession.update(newRefresh.getBody());
    return new TokenPair(newAccess, newRefresh);
  }

  @Override
  public void delete(OpenRefreshToken refreshToken) {
    repository.deleteByRefreshToken(refreshToken.getBody());
  }

  public void deleteExceed(RefreshSession presentSession) {
    Long userId = presentSession.getUserId();
    if (Objects.equals(repository.countByUserId(userId), MAX_COUNT_OF_SESSIONS_BY_USER)) {
      repository.deleteAllByUserId(userId);
    }
  }

  @Scheduled(cron = "0 0 1 * * ?")
  public void removeExpiredSessions() {
    repository.deleteExpired();
  }
}
