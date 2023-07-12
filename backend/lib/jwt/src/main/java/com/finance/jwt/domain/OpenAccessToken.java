package com.finance.jwt.domain;

import com.finance.jwt.domain.token.TokenMetadata;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@PropertySource("classpath:default-access-jwt.properties")
@Getter
public class OpenAccessToken extends OpenJwtToken implements Authentication {
  private boolean authenticated = false;
  private final Collection<GrantedAuthority> authorities;
  private final Long userId;

  public OpenAccessToken(String body) {
    super(body, TokenMetadata.ACCESS);

    this.userId = injectedClaims.get("userId", Long.class);
    this.authorities = new ArrayList<>();
    Collection<?> collection = injectedClaims.get("authorities", Collection.class);
    if (collection != null) {
      for (Object o : collection) {
        Collection<String> authorityNames = ((Map<String, String>) o).values();
        for (String authority : authorityNames) {
          authorities.add(new SimpleGrantedAuthority(authority));
        }
      }
    }
  }

  public static OpenAccessToken generate(Claims claims) {
    return new OpenAccessToken(generateBody(claims, TokenMetadata.ACCESS));
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    OpenAccessToken that = (OpenAccessToken) o;
    return authenticated == that.authenticated && Objects.equals(authorities, that.authorities) && Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), authenticated, authorities, userId);
  }

  @Override
  public String toString() {
    return "OpenAccessToken{" +
      "authenticated=" + authenticated +
      ", authorities=" + authorities +
      ", userId=" + userId +
      ", body='" + body + '\'' +
      ", injectedClaims=" + injectedClaims +
      '}';
  }
}
