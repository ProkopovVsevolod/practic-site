package com.finance.jwt.config;

import com.finance.jwt.security.authentication.AuthenticateService;
import com.finance.jwt.security.authentication.AuthenticationServiceImpl;
import com.finance.jwt.security.refresh.RefreshSessionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@Import({AccessJwtConfig.class, RefreshJwtConfig.class})
@EnableJpaRepositories(basePackages = "com.finance.jwt.security.authentication")
public class AuthenticationConfig {
  @Bean
  public AuthenticateService authenticateService(RefreshSessionService refreshSessionService,
                                                 AuthenticationManager authenticationManager) {
    return new AuthenticationServiceImpl(refreshSessionService, authenticationManager);
  }
}
