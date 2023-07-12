package com.finance.budget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = "com.finance.lib.budget.domain.entity")
@EnableJpaRepositories(basePackages = "com.finance.budget.infrastructure.repository.contract")
public class BudgetServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(BudgetServiceApplication.class, args);
  }
}