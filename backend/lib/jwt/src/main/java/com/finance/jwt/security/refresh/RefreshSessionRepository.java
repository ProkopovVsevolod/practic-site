package com.finance.jwt.security.refresh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RefreshSessionRepository extends JpaRepository<RefreshSession, Long> {
  List<RefreshSession> findByUserId(Long userId);

  RefreshSession findByRefreshToken(String refreshToken);

  void deleteAllByUserId(Long userId);

  void deleteByUserId(Long userId);

  void deleteByRefreshToken(String refreshToken);

  boolean existsByRefreshToken(String refreshToken);

  Long countByUserId(Long userId);

  @Query("delete from RefreshSession where expired < current_timestamp")
  void deleteExpired();

  @Query(
    "update RefreshSession set refreshToken = :newToken " +
    "where userId = :userId " +
      "and userIp = :userIp " +
      "and userBrowserFingerPrint = :userBrowserFingerPrint"
  )
  void updateSession(Long userId, String userIp, String userBrowserFingerPrint, String newToken);
}
