package com.finance.jwt.domain;

import com.finance.jwt.config.token.TokenMetadata;
import com.finance.jwt.config.token.time.CommonInterval;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Getter
public abstract class OpenJwtToken implements Authentication {
  protected final String body;
  protected final Claims injectedClaims;
  private final Collection<GrantedAuthority> authorities;
  private boolean authenticated = false;

  public OpenJwtToken(String body, TokenMetadata metadata) {
    this.body = body;
    this.injectedClaims = Jwts.parserBuilder()
      .setSigningKey(metadata.getSecretKey())
      .build()
      .parseClaimsJws(body)
      .getBody();

    this.authorities = new ArrayList<>();
    Collection<?> collection = injectedClaims.get("authorities", Collection.class);
    if (collection != null) {
      for (Object o : collection) {
        Collection<String> authorityNames = ((Map<String, String>) o).values();
        for (String authority : authorityNames) {
          authorities.add(new SimpleGrantedAuthority("ROLE_" + authority));
        }
      }
    }
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

  protected static String generateBody(Claims claims, Date expired) {
    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(new Date())
      .setExpiration(expired)
      .signWith(TokenMetadata.REFRESH.getSecretKey())
      .compact();
  }

  public boolean isValid() {
    if (body == null) return false;
    return !injectedClaims.getExpiration().before(new Date());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getDetails() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return body;
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }

  @Override
  public String getName() {
    return "OpenJwtToken";
  }

  @Override
  public String toString() {
    return "OpenJwtToken{" +
      "body='" + body + '\'' +
      ", authenticated=" + authenticated +
      '}';
  }
}
