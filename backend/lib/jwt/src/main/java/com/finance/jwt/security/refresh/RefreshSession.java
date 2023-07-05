package com.finance.jwt.security.refresh;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "refresh_session", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"userId", "userIp", "userBrowserFingerPrint"})
})
public class RefreshSession {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(columnDefinition = "text")
  private String refreshToken;

  private Long userId;

  private String userIp;

  private String userBrowserFingerPrint;

  @Column(name = "expired", columnDefinition = "timestamp")
  private Date expired;

  public RefreshSession(Long userId,
                        String userIp,
                        String userBrowserFingerPrint,
                        String refreshToken,
                        Date expired) {
    this.userId = userId;
    this.userIp = userIp;
    this.userBrowserFingerPrint = userBrowserFingerPrint;
    this.refreshToken = refreshToken;
    this.expired = expired;
  }

  public void update(String newToken) {
    this.refreshToken = newToken;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RefreshSession that = (RefreshSession) o;
    return Objects.equals(userId, that.userId) && Objects.equals(userIp, that.userIp) && Objects.equals(userBrowserFingerPrint, that.userBrowserFingerPrint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userIp, userBrowserFingerPrint);
  }
}
