package com.finance.lib.budget.dto.operation.expense.plan;

import com.finance.lib.budget.dto.amount.AmountDto;
import com.finance.lib.budget.dto.operation.expense.ExpenseCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpensePlanCommonRequestDto {
  private AmountDto limit;
  private ExpenseCategoryDto category;
}
