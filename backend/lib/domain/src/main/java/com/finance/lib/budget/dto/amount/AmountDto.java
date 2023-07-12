package com.finance.lib.budget.dto.amount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmountDto {
  private BigDecimal value;
  private CurrencyDto currency;
}
