package com.finance.budget.domain.amount;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Currency {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String code;

  private String name;

  private Character symbol;
}
