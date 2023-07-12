package com.finance.lib.budget.dto.operation.income;

import com.finance.lib.budget.dto.amount.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeCommonRequestDto {
  private String name;
  private String description;
  private String source;
  private IncomeCategoryDto incomeCategory;
  private AmountDto amount;
}