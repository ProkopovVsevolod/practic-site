package com.finance.analysis.config;

import com.finance.jwt.config.AccessJwtConfig;
import com.finance.jwt.security.configurer.SecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Import(AccessJwtConfig.class)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                 SecurityConfigurer configurer) throws Exception {
    return configurer.createChain(http, this::authorizeRequest);
  }

  private void authorizeRequest(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry reg) {
    reg.requestMatchers("/api/v1/analysis/budgets/{budget-id}/balance").authenticated();
    reg.requestMatchers("/api/v1/analysis/budgets/{budget-id}/diff/{year}-{month}:{duration}").authenticated();
    reg.requestMatchers("/api/v1/analysis/budgets/{budget-id}/operations/{year}-{month}:{duration}").authenticated();
    reg.requestMatchers("/api/v1/analysis/budgets/{budget-id}/planned-by-actual-incomes/{year}-{month}/{category}").authenticated();
    reg.requestMatchers("/api/v1/analysis/budgets/{budget-id}/planned-by-actual-expenses/{year}-{month}/{category}").authenticated();
  }
}