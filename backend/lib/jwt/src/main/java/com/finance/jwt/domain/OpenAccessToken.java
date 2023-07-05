package com.finance.jwt.domain;

import com.finance.jwt.config.token.TokenMetadata;
import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:default-access-jwt.properties")
public class OpenAccessToken extends OpenJwtToken {

  public OpenAccessToken(String body) {
    super(body, TokenMetadata.ACCESS);
  }

  public static OpenAccessToken generate(Claims claims) {
    return new OpenAccessToken(generateBody(claims, TokenMetadata.ACCESS));
  }
}
