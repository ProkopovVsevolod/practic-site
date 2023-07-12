package com.finance.lib.budget.mapper;

import com.finance.lib.budget.dto.budget.BudgetCommonRequestDto;
import com.finance.lib.budget.dto.budget.BudgetCommonResponseDto;
import com.finance.lib.budget.dto.budget.BudgetCreateResponseDto;
import com.finance.lib.budget.domain.entity.Budget;
import com.finance.lib.budget.domain.entity.amount.Amount;
import com.finance.lib.budget.dto.ListDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class BudgetMapper {
  private final IdMapper idMapper;
  private final AmountMapper amountMapper;
  private final PeriodMapper periodMapper;
  private final IncomeMapper incomeMapper;
  private final IncomePlanMapper incomePlanMapper;
  private final ExpenseMapper expenseMapper;
  private final ExpensePlanMapper expensePlanMapper;

  public Budget convertBudgetCommonRequestDtoToBudget(Long userId, BudgetCommonRequestDto budgetCommonRequestDto) {
    return new Budget(
      userId,
      budgetCommonRequestDto.getFinancialGoal(),
      periodMapper.convertDurationToNowPeriod(budgetCommonRequestDto.getDuration()),
      new Amount(new BigDecimal(0), amountMapper.convert(budgetCommonRequestDto.getCurrency()))
    );
  }

  public BudgetCreateResponseDto convertBudgetToBudgetCreateResponseDto(Budget budget) {
    return new BudgetCreateResponseDto(
      idMapper.convert(budget.getCompositeId()),
      budget.getFinancialGoal(),
      periodMapper.convert(budget.getPeriod()),
      amountMapper.convert(budget.getBalance().getAmountCurrency())
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
      periodMapper.convert(budget.getPeriod()),
      amountMapper.convert(budget.getBalance()),
      incomeMapper.convertIncomeListToIncomeCommonResponseDtoList(budget.getIncomes()),
      expenseMapper.convertExpenseListToExpenseCommonResponseDtoList(budget.getExpenses()),
      incomePlanMapper.convertIncomePlanListToIncomePlanCommonResponseDtoList(budget.getIncomePlans()),
      expensePlanMapper.convertExpensePlanListToExpensePlanCommonResponseDtoList(budget.getExpensePlans())
    );
  }
}
