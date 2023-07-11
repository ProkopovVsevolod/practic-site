package com.finance.user.view.mapper;

import com.finance.jwt.domain.TokenPair;
import com.finance.user.view.dto.TokenPairDto;
import com.finance.user.view.dto.LoginDto;
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
