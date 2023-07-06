package com.finance.budget.domain.amount;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Embeddable
public class Amount {
  private BigDecimal amount;

  @OneToOne
  @Column(name = "amount_currency")
  private Currency amountCurrency;
}
