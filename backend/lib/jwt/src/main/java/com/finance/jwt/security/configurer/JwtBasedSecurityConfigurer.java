package com.finance.jwt.security.configurer;

import com.finance.jwt.security.filter.AccessTokenFilter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JwtBasedSecurityConfigurer implements SecurityConfigurer {
  private final AccessTokenFilter accessTokenFilter;
  private final List<AuthorizeHttpRequestsCustomizer> commonCustomizers;

  public JwtBasedSecurityConfigurer(AccessTokenFilter accessTokenFilter,
                                    AuthorizeHttpRequestsCustomizer... customizers) {
    this.accessTokenFilter = accessTokenFilter;
    commonCustomizers = new ArrayList<>(Arrays.asList(customizers));
  }

  @Override
  public SecurityFilterChain createChain(HttpSecurity http,
                                         Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizer) throws Exception {
    return http
      .csrf().disable()
      .httpBasic().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeHttpRequests(reg -> {
        commonCustomizers.forEach(c -> c.customize(reg));
        customizer.customize(reg);
      })
      .addFilterBefore(accessTokenFilter, AuthorizationFilter.class)
      .build();
  }
}
