package com.finance.jwt.config;

import com.finance.jwt.config.token.TokenMetadata;
import com.finance.jwt.resolver.RefreshTokenResolver;
import com.finance.jwt.security.refresh.CommonRefreshSessionService;
import com.finance.jwt.security.refresh.RefreshSessionRepository;
import com.finance.jwt.security.refresh.RefreshSessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
@EntityScan(basePackages = "com.finance.jwt.security.refresh")
@EnableJpaRepositories(basePackages = "com.finance.jwt.security.refresh")
@PropertySource("classpath:default-refresh-jwt.properties")
public class RefreshJwtConfig {

  public RefreshJwtConfig(@Value("${jwt.token.refresh.secret.default}") String refreshSecret) {
    TokenMetadata.REFRESH.setSecret(refreshSecret);
  }

  @Bean
  public RefreshSessionService refreshSessionService(RefreshSessionRepository refreshSessionRepository) {
    return new CommonRefreshSessionService(refreshSessionRepository);
  }

  @Bean
  public RefreshTokenResolver refreshTokenResolver() {
    return new RefreshTokenResolver();
  }
}
