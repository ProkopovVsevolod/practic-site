package com.finance.jwt.config.token;

import com.finance.jwt.config.token.time.CommonInterval;
import com.finance.jwt.config.token.time.Interval;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

import java.security.Key;

@Getter
public enum TokenMetadata {
  ACCESS("Authorization", 1000 * 60 * 15),
  REFRESH("Refresh", 1000 * 60 * 60 * 24 * 15);

  private final String header;
  private Key secretKey;
  private final long validityDateInMilliseconds;

  TokenMetadata(String header, long validityDateInMilliseconds) {
    this.header = header;
    this.validityDateInMilliseconds = validityDateInMilliseconds;
  }

  public Interval expiredInterval() {
    return new CommonInterval(validityDateInMilliseconds);
  }

  public Key getSecretKey() {
    if (secretKey == null) {
      throw new IllegalStateException("SecretKey by " + header + " is not supported");
    }
    return secretKey;
  }

  public synchronized void setSecret(String secret) {
    secret = Encoders.BASE64.encode(secret.getBytes());
    this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  @Override
  public String toString() {
    return "TokenMetadata{" +
      "header='" + header + '\'' +
      ", secretKey=[PROTECTED]" +
      ", validityDateInMilliseconds=" + validityDateInMilliseconds +
      '}';
  }
}
