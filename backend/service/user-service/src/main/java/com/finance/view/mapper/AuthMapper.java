package com.finance.view.mapper;

import com.finance.jwt.domain.TokenPair;
import com.finance.view.dto.LoginDto;
import com.finance.view.dto.TokenPairDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
  public UsernamePasswordAuthenticationToken convert(LoginDto loginDto) {
    return new UsernamePasswordAuthenticationToken(
      loginDto.getEmail(),
      loginDto.getPassword()
    );
  }

  public TokenPairDto convert(TokenPair tokenPair) {
    return new TokenPairDto(
      tokenPair.getOpenAccessToken().getBody(),
      tokenPair.getOpenRefreshToken().getBody()
    );
  }
}
