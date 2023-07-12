package com.finance.lib.budget.dto.budget;

import com.finance.lib.budget.dto.DurationDto;
import com.finance.lib.budget.dto.amount.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetCommonRequestDto {
  private String financialGoal;
  private DurationDto duration;
  private CurrencyDto currency;
}
