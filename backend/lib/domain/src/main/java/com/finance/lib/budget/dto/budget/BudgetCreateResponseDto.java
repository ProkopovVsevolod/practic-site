package com.finance.lib.budget.dto.budget;


import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.finance.lib.budget.dto.CompositeIdDto;
import com.finance.lib.budget.dto.PeriodDto;
import com.finance.lib.budget.dto.amount.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetCreateResponseDto {
  @JsonUnwrapped
  private CompositeIdDto compositeId;
  private String financialGoal;
  private PeriodDto period;
  private CurrencyDto balanceCurrency;
}
