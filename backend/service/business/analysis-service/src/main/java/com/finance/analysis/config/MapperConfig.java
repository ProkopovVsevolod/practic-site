package com.finance.analysis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(com.finance.lib.budget.config.MapperConfig.class)
public class MapperConfig {
}
