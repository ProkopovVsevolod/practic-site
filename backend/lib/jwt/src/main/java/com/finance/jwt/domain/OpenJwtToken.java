package com.finance.jwt.domain;

import com.finance.jwt.domain.token.TokenMetadata;
import com.finance.jwt.domain.token.time.CommonInterval;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Getter
public abstract class OpenJwtToken {
  protected final String body;
  protected final Claims injectedClaims;

  public OpenJwtToken(String body, TokenMetadata metadata) {
    this.body = body;
    this.injectedClaims = Jwts.parserBuilder()
      .setSigningKey(metadata.getSecretKey())
      .build()
      .parseClaimsJws(body)
      .getBody();
  }

  protected static String generateBody(Claims claims, TokenMetadata metadata) {
    var dateGroup = new CommonInterval(metadata.getValidityDateInMilliseconds());
    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(dateGroup.getCreated())
      .setExpiration(dateGroup.getExpired())
      .signWith(metadata.getSecretKey())
      .compact();
  }

  public boolean isValid() {
    if (body == null) return false;
    return !injectedClaims.getExpiration().before(new Date());
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OpenJwtToken that = (OpenJwtToken) o;
    return Objects.equals(body, that.body) && Objects.equals(injectedClaims, that.injectedClaims);
  }

  @Override
  public int hashCode() {
    return Objects.hash(body, injectedClaims);
  }
}
