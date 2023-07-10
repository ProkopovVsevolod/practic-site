package com.finance.user.security.config;

import com.finance.jwt.config.AuthenticationConfig;
import com.finance.jwt.security.configurer.SecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Import(AuthenticationConfig.class)
public class SecurityConfig {
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService service) {
    var daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(service);
    return daoAuthenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                 SecurityConfigurer configurer) throws Exception {
    return configurer.createChain(http, this::authorizeRequest);
  }

  private void authorizeRequest(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry reg) {
    reg.requestMatchers("/api/v1/users/signup").permitAll();
    reg.requestMatchers("/api/v1/users/login").permitAll();
    reg.requestMatchers("/api/v1/users/refresh").permitAll();
    reg.requestMatchers("/api/v1/users/logout").permitAll();
  }
}
