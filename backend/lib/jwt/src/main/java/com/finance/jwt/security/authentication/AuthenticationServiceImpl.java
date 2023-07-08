package com.finance.jwt.security.authentication;

import com.finance.jwt.domain.OpenAccessToken;
import com.finance.jwt.domain.OpenRefreshToken;
import com.finance.jwt.domain.TokenPair;
import com.finance.jwt.resolver.RequestMetadata;
import com.finance.jwt.security.refresh.RefreshSessionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Transactional
public class AuthenticationServiceImpl implements AuthenticateService {
  private final RefreshSessionService refreshSessionService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationServiceImpl(RefreshSessionService refreshSessionService,
                                   AuthenticationManager authenticationManager) {
    this.refreshSessionService = refreshSessionService;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public TokenPair login(UsernamePasswordAuthenticationToken authentication,
                         RequestMetadata requestMetadata) {
    authentication = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(authentication);
    UserDetailsExtend principal = (UserDetailsExtend) authentication.getPrincipal();

    Claims claims = new DefaultClaims(Map.of(
      "authorities", authentication.getAuthorities(),
      "userId", principal.getId()
    ));
    claims.setSubject(principal.getUsername());

    OpenAccessToken accessToken = OpenAccessToken.generate(claims);
    OpenRefreshToken refreshToken = OpenRefreshToken.generate(claims);

    refreshSessionService.create(requestMetadata, principal.getId(), refreshToken);
    return new TokenPair(accessToken, refreshToken);
  }

  @Override
  public TokenPair refresh(OpenRefreshToken refreshToken) {
    String body = refreshToken.getBody();
    Date expiration = refreshToken.getInjectedClaims().getExpiration();
    Claims claims = refreshToken.getInjectedClaims();

    OpenAccessToken newAccess = OpenAccessToken.generate(claims);
    OpenRefreshToken newRefresh = OpenRefreshToken.generate(claims, expiration);

    refreshSessionService.refresh(body, newRefresh.getBody());

    return new TokenPair(newAccess, newRefresh);
  }

  @Override
  public void logout(OpenRefreshToken refreshToken) {
    refreshSessionService.delete(refreshToken);
  }
}
