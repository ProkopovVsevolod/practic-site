package com.finance.budget.view.dto.budget;

import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.PeriodDto;
import com.finance.budget.view.dto.amount.AmountDto;
import com.finance.budget.view.dto.expense.ExpenseCommonResponseDto;
import com.finance.budget.view.dto.expense.ExpensePlanCommonResponseDto;
import com.finance.budget.view.dto.income.IncomeCommonResponseDto;
import com.finance.budget.view.dto.income.IncomePlanCommonResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetCommonResponseDto {
  private Long id;
  private String financialGoal;
  private Long userId;
  private PeriodDto period;
  private AmountDto balance;
  private ListDto<IncomeCommonResponseDto> incomes;
  private ListDto<ExpenseCommonResponseDto> expenses;
  private ListDto<IncomePlanCommonResponseDto> incomePlans;
  private ListDto<ExpensePlanCommonResponseDto> expensePlans;
}
