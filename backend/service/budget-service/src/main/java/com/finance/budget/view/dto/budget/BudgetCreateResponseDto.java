package com.finance.budget.view.dto.budget;


import com.finance.budget.view.dto.PeriodDto;
import com.finance.budget.view.dto.amount.CurrencyDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetCreateResponseDto {
  private Long id;
  private String financialGoal;
  private Long userId;
  private PeriodDto period;
  private CurrencyDto balanceCurrency;
}