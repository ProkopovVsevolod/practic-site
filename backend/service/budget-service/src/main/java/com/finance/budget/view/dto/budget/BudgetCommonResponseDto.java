package com.finance.budget.view.dto.budget;

import com.finance.budget.view.dto.CompositeIdDto;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.PeriodDto;
import com.finance.budget.view.dto.amount.AmountDto;
import com.finance.budget.view.dto.expense.ExpenseCommonResponseDto;
import com.finance.budget.view.dto.expense.plan.ExpensePlanCommonResponseDto;
import com.finance.budget.view.dto.income.IncomeCommonResponseDto;
import com.finance.budget.view.dto.income.plan.IncomePlanCommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetCommonResponseDto {
  private CompositeIdDto compositeId;
  private String financialGoal;
  private PeriodDto period;
  private AmountDto balance;
  private ListDto<IncomeCommonResponseDto> incomes;
  private ListDto<ExpenseCommonResponseDto> expenses;
  private ListDto<IncomePlanCommonResponseDto> incomePlans;
  private ListDto<ExpensePlanCommonResponseDto> expensePlans;
}
