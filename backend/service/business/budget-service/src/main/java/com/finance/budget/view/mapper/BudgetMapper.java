package com.finance.budget.view.mapper;

import com.finance.budget.domain.Budget;
import com.finance.budget.domain.amount.Amount;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.budget.BudgetCommonRequestDto;
import com.finance.budget.view.dto.budget.BudgetCommonResponseDto;
import com.finance.budget.view.dto.budget.BudgetCreateResponseDto;
import com.finance.budget.view.mapper.enums.AmountEnumMapper;
import com.finance.budget.view.mapper.enums.PeriodEnumMapper;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class BudgetMapper {
  private final IdMapper idMapper;
  private final AmountEnumMapper amountEnumMapper;
  private final PeriodEnumMapper periodEnumMapper;
  private final IncomeMapper incomeMapper;
  private final IncomePlanMapper incomePlanMapper;
  private final ExpenseMapper expenseMapper;
  private final ExpensePlanMapper expensePlanMapper;

  public Budget convertBudgetCommonRequestDtoToBudget(Long userId, BudgetCommonRequestDto budgetCommonRequestDto) {
    return new Budget(
      userId,
      budgetCommonRequestDto.getFinancialGoal(),
      periodEnumMapper.convert(budgetCommonRequestDto.getPeriod()),
      new Amount(new BigDecimal(0), amountEnumMapper.convert(budgetCommonRequestDto.getCurrency()))
    );
  }

  public BudgetCreateResponseDto convertBudgetToBudgetCreateResponseDto(Budget budget) {
    return new BudgetCreateResponseDto(
      idMapper.convert(budget.getCompositeId()),
      budget.getFinancialGoal(),
      periodEnumMapper.convert(budget.getPeriod()),
      amountEnumMapper.convert(budget.getBalance().getAmountCurrency())
    );
  }

  public ListDto<BudgetCommonResponseDto> convertBudgetListToBudgetCommonResponseDtoList(List<Budget> budgetList,
                                                                                         Integer offset,
                                                                                         Integer diapason) {
    List<BudgetCommonResponseDto> budgetCommonResponseDtos = budgetList.stream()
      .map(this::convertBudgetToBudgetCommonResponseDto)
      .toList();
    return new ListDto<>(budgetCommonResponseDtos, offset, diapason);
  }

  public BudgetCommonResponseDto convertBudgetToBudgetCommonResponseDto(Budget budget) {
    return new BudgetCommonResponseDto(
      idMapper.convert(budget.getCompositeId()),
      budget.getFinancialGoal(),
      periodEnumMapper.convert(budget.getPeriod()),
      amountEnumMapper.convert(budget.getBalance()),
      incomeMapper.convertIncomeListToIncomeCommonResponseDtoList(budget.getIncomes()),
      expenseMapper.convertExpenseListToExpenseCommonResponseDtoList(budget.getExpenses()),
      incomePlanMapper.convertIncomePlanListToIncomePlanCommonResponseDtoList(budget.getIncomePlans()),
      expensePlanMapper.convertExpensePlanListToExpensePlanCommonResponseDtoList(budget.getExpensePlans())
    );
  }
}
