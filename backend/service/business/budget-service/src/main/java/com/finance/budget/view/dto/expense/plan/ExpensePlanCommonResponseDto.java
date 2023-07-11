package com.finance.budget.view.dto.expense.plan;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.finance.budget.view.dto.CompositeIdDto;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.PeriodDto;
import com.finance.budget.view.dto.amount.AmountDto;
import com.finance.budget.view.dto.expense.ExpenseCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpensePlanCommonResponseDto {
  @JsonUnwrapped
  private CompositeIdDto compositeId;
  private AmountDto limit;
  private PeriodDto period;
  private ListDto<ExpenseCategoryDto> categories;
}
