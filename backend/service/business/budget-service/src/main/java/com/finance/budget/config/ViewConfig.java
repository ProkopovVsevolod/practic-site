package com.finance.budget.config;

import com.finance.exception.ExceptionHandlerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ExceptionHandlerConfig.class)
public class ViewConfig {
}
