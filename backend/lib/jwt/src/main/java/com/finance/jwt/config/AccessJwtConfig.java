package com.finance.jwt.config;

import com.finance.jwt.config.token.TokenMetadata;
import com.finance.jwt.resolver.AccessTokenResolver;
import com.finance.jwt.security.authentication.JwtAuthenticationProvider;
import com.finance.jwt.security.configurer.CommonAuthorizeHttpRequestsCustomizer;
import com.finance.jwt.security.configurer.JwtBasedSecurityConfigurer;
import com.finance.jwt.security.filter.AccessTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;

@Configuration
@PropertySource("classpath:default-access-jwt.properties")
public class AccessJwtConfig {

  public AccessJwtConfig(@Value("${jwt.token.access.secret.default}") String accessSecret) {
    TokenMetadata.ACCESS.setSecret(accessSecret);
  }

  @Bean
  public JwtBasedSecurityConfigurer jwtBasedSecurityConfigurer(AuthenticationManager authenticationManager) {
    CommonAuthorizeHttpRequestsCustomizer customizer = new CommonAuthorizeHttpRequestsCustomizer();
    AccessTokenResolver accessTokenResolver = new AccessTokenResolver();
    AccessTokenFilter accessTokenFilter = new AccessTokenFilter(authenticationManager, accessTokenResolver);
    return new JwtBasedSecurityConfigurer(accessTokenFilter, customizer);
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationProvider... providers) {
    JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider();
    AuthenticationProvider[] extendedProviders = new AuthenticationProvider[providers.length + 1];
    System.arraycopy(providers, 0, extendedProviders, 0, providers.length);
    extendedProviders[providers.length] = jwtAuthenticationProvider;
    return new ProviderManager(extendedProviders);
  }
}
