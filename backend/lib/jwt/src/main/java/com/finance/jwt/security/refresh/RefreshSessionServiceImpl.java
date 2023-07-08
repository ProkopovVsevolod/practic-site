package com.finance.jwt.security.refresh;

import com.finance.exception.IllegalTokenException;
import com.finance.jwt.domain.OpenRefreshToken;
import com.finance.jwt.resolver.RequestMetadata;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Transactional
public class RefreshSessionServiceImpl implements RefreshSessionService {
  private static final Long MAX_COUNT_OF_SESSIONS_BY_USER = 2L;
  private final RefreshSessionRepository repository;

  @Autowired
  public RefreshSessionServiceImpl(RefreshSessionRepository repository) {
    this.repository = repository;
  }

  @Override
  public void create(RequestMetadata requestMetadata, Long userId, OpenRefreshToken refreshToken) {
    RefreshSession refreshSession = new RefreshSession(
      userId,
      requestMetadata.getRemoteAddress(),
      requestMetadata.getUserAgent(),
      refreshToken.getBody(),
      refreshToken.getInjectedClaims().getExpiration()
    );
    deleteExceed(refreshSession);
    repository.save(refreshSession);
  }

  @Override
  public void refresh(@NonNull String oldToken, @NonNull String newToken) {
    RefreshSession presentSession = repository.findByRefreshToken(oldToken)
      .orElseThrow(() -> new IllegalTokenException("Cannot refresh token, is not present", oldToken));

    presentSession.update(newToken);
  }

  @Override
  public void delete(OpenRefreshToken refreshToken) {
    Optional<RefreshSession> byRefreshToken = repository.findByRefreshToken(refreshToken.getBody());
    if (byRefreshToken.isEmpty()) {
      throw new IllegalTokenException("Cannot delete token, is not present", refreshToken.getBody());
    }
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
