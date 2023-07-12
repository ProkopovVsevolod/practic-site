package com.finance.analysis.config;

import com.finance.exception.ExceptionHandlerConfig;
import com.finance.lib.budget.config.MapperConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
  ExceptionHandlerConfig.class,
  MapperConfig.class
})
public class ViewConfig {
}
