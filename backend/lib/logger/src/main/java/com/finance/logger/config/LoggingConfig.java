package com.finance.logger.config;

import com.finance.logger.aop.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LoggingConfig {
  @Bean
  public LoggingAspect loggingAspect() {
    return new LoggingAspect();
  }
}
