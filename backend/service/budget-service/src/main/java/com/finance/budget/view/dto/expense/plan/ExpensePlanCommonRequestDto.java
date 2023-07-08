package com.finance.budget.view.dto.expense.plan;

import com.finance.budget.view.dto.PeriodDto;
import com.finance.budget.view.dto.amount.AmountDto;
import com.finance.budget.view.dto.expense.ExpenseCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpensePlanCommonRequestDto {
  private AmountDto limit;
  private PeriodDto period;
  private List<ExpenseCategoryDto> categories;
}
