package com.finance.budget.security.config;

import com.finance.jwt.config.AccessJwtConfig;
import com.finance.jwt.security.configurer.SecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
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

  private void authorizeRequest(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
    registry.requestMatchers(HttpMethod.POST, "/api/v1/users/budgets").authenticated();
    registry.requestMatchers(HttpMethod.GET, "/api/v1/users/budgets").authenticated();
    registry.requestMatchers(HttpMethod.PUT, "/api/v1/users/budgets/{budget-id}").authenticated();
    registry.requestMatchers(HttpMethod.DELETE, "/api/v1/users/budgets/{budget-id}").authenticated();

    registry.requestMatchers(HttpMethod.POST, "/api/v1/users/incomes").authenticated();
    registry.requestMatchers(HttpMethod.GET, "/api/v1/users/incomes").authenticated();
    registry.requestMatchers(HttpMethod.PUT, "/api/v1/users/incomes/{income-id}").authenticated();
    registry.requestMatchers(HttpMethod.DELETE, "/api/v1/users/incomes/{income-id}").authenticated();

    registry.requestMatchers(HttpMethod.POST, "/api/v1/users/expenses").authenticated();
    registry.requestMatchers(HttpMethod.GET, "/api/v1/users/expenses").authenticated();
    registry.requestMatchers(HttpMethod.PUT, "/api/v1/users/expenses/{expense-id}").authenticated();
    registry.requestMatchers(HttpMethod.DELETE, "/api/v1/users/expenses/{expense-id}").authenticated();
  }
}
