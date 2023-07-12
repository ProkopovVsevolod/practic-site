package com.finance.user.view.controller;

import com.finance.jwt.domain.OpenRefreshToken;
import com.finance.jwt.domain.TokenPair;
import com.finance.jwt.resolver.RequestMetadata;
import com.finance.jwt.security.authentication.AuthenticateService;
import com.finance.user.view.dto.LoginDto;
import com.finance.user.view.dto.TokenPairDto;
import com.finance.user.view.mapper.AuthMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus(HttpStatus.OK)
public class AuthController {
  private final AuthenticateService authenticateService;
  private final AuthMapper authMapper;

  @Autowired
  public AuthController(AuthenticateService authenticateService,
                        AuthMapper authMapper) {
    this.authenticateService = authenticateService;
    this.authMapper = authMapper;
  }

  @PostMapping("/api/v1/users/login")
  public TokenPairDto login(HttpServletRequest request, @RequestBody LoginDto loginDto) {
    var authToken = authMapper.convert(loginDto);
    RequestMetadata requestMetadata = new RequestMetadata(request);
    TokenPair tokenPair = authenticateService.login(authToken, requestMetadata);
    return authMapper.convert(tokenPair);
  }

  @PostMapping("/api/v1/users/refresh")
  public TokenPairDto refresh(@CookieValue("Refresh") String refreshToken) {
    OpenRefreshToken openRefreshToken = new OpenRefreshToken(refreshToken);
    TokenPair tokenPair = authenticateService.refresh(openRefreshToken);
    return authMapper.convert(tokenPair);
  }

  @PostMapping("/api/v1/users/logout")
  public void logout(@CookieValue("Refresh") String refreshToken) {
    OpenRefreshToken openRefreshToken = new OpenRefreshToken(refreshToken);
    authenticateService.logout(openRefreshToken);
  }
}
