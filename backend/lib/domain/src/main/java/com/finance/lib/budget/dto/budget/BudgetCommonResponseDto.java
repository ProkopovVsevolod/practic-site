package com.finance.lib.budget.dto.budget;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.finance.lib.budget.dto.CompositeIdDto;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.PeriodDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import com.finance.lib.budget.dto.operation.expense.ExpenseCommonResponseDto;
import com.finance.lib.budget.dto.operation.expense.plan.ExpensePlanCommonResponseDto;
import com.finance.lib.budget.dto.operation.income.IncomeCommonResponseDto;
import com.finance.lib.budget.dto.operation.income.plan.IncomePlanCommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetCommonResponseDto {
  @JsonUnwrapped
  private CompositeIdDto compositeId;
  private String financialGoal;
  private PeriodDto period;
  private AmountDto balance;
  private ListDto<IncomeCommonResponseDto> incomes;
  private ListDto<ExpenseCommonResponseDto> expenses;
  private ListDto<IncomePlanCommonResponseDto> incomePlans;
  private ListDto<ExpensePlanCommonResponseDto> expensePlans;
}
