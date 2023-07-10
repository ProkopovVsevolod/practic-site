package com.finance.budget.security.config;

import com.finance.jwt.config.AccessJwtConfig;
import com.finance.jwt.security.configurer.SecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

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
    reg.requestMatchers(POST, "/api/v1/budgets").authenticated();
    reg.requestMatchers(GET, "/api/v1/budgets").authenticated();
    reg.requestMatchers(PUT, "/api/v1/budgets/{budget-id}").authenticated();
    reg.requestMatchers(DELETE, "/api/v1/budgets/{budget-id}").authenticated();
    reg.requestMatchers(POST, "/api/v1/budgets/{budget-id}/incomes/{income-id}").authenticated();
    reg.requestMatchers(POST, "/api/v1/budgets/{budget-id}/expenses/{expense-id}").authenticated();
    reg.requestMatchers(POST, "/api/v1/budgets/{budget-id}/income-plans/{income-plan-id}").authenticated();
    reg.requestMatchers(POST, "/api/v1/budgets/{budget-id}/expense-plans/{expense-plan-id}").authenticated();

    reg.requestMatchers(POST, "/api/v1/expenses").authenticated();
    reg.requestMatchers(GET, "/api/v1/expenses").authenticated();
    reg.requestMatchers(PUT, "/api/v1/expenses/{expense-id}").authenticated();
    reg.requestMatchers(DELETE, "/api/v1/expenses/{expense-id}").authenticated();

    reg.requestMatchers(POST, "/api/v1/expense-plans").authenticated();
    reg.requestMatchers(GET, "/api/v1/expense-plans").authenticated();
    reg.requestMatchers(PUT, "/api/v1/expense-plans/{expense-plan-id}").authenticated();
    reg.requestMatchers(DELETE, "/api/v1/expense-plans/{expense-plan-id}").authenticated();

    reg.requestMatchers(POST, "/api/v1/incomes").authenticated();
    reg.requestMatchers(GET, "/api/v1/incomes").authenticated();
    reg.requestMatchers(PUT, "/api/v1/incomes/{income-id}").authenticated();
    reg.requestMatchers(DELETE, "/api/v1/incomes/{income-id}").authenticated();

    reg.requestMatchers(POST, "/api/v1/income-plans").authenticated();
    reg.requestMatchers(GET, "/api/v1/income-plans").authenticated();
    reg.requestMatchers(PUT, "/api/v1/income-plans/{income-plan-id}").authenticated();
    reg.requestMatchers(DELETE, "/api/v1/income-plans/{income-plan-id}").authenticated();
  }
}
