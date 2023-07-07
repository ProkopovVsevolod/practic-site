package com.finance.budget.domain.amount;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Amount {
  private BigDecimal amount;

  @Enumerated
  private Currency amountCurrency;
}
