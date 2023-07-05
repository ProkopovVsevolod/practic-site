package com.finance.jwt.domain;

import lombok.Getter;

@Getter
public class TokenPair {
  private final OpenAccessToken openAccessToken;
  private final OpenRefreshToken openRefreshToken;

  public TokenPair(OpenAccessToken openAccessToken,
                   OpenRefreshToken openRefreshToken) {
    this.openAccessToken = openAccessToken;
    this.openRefreshToken = openRefreshToken;
  }
}
