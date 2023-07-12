package com.finance.jwt.domain;

import com.finance.jwt.domain.token.TokenMetadata;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;

import java.util.Date;

@Getter
public class OpenRefreshToken extends OpenJwtToken {
  public OpenRefreshToken(String body) {
    super(body, TokenMetadata.REFRESH);
  }

  public static OpenRefreshToken generate(Claims claims) {
    return new OpenRefreshToken(generateBody(claims, TokenMetadata.REFRESH));
  }

  public static OpenRefreshToken generate(Claims claims, Date expired) {
    return new OpenRefreshToken(generateBody(claims, expired));
  }

  private static String generateBody(Claims claims, Date expired) {
    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(new Date())
      .setExpiration(expired)
      .signWith(TokenMetadata.REFRESH.getSecretKey())
      .compact();
  }
}
