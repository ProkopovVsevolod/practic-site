package com.finance.budget.view.dto.income;

import com.finance.budget.view.dto.amount.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeCreateRequestDto {
  private String name;
  private String description;
  private String source;
  private IncomeCategoryDto incomeCategory;
  private AmountDto amount;
}