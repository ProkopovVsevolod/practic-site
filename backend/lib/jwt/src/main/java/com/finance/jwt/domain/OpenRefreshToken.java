package com.finance.jwt.domain;

import com.finance.jwt.config.token.TokenMetadata;
import io.jsonwebtoken.Claims;
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
}
