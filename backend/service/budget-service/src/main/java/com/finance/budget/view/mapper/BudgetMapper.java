package com.finance.budget.view.mapper;

import com.finance.budget.domain.Budget;
import com.finance.budget.view.dto.budget.BudgetCommonResponseDto;
import com.finance.budget.view.dto.budget.BudgetCommonRequestDto;
import com.finance.budget.view.dto.budget.BudgetCreateResponseDto;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.mapper.enums.AmountEnumMapper;
import com.finance.budget.view.mapper.enums.PeriodEnumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BudgetMapper {
  private final AmountEnumMapper amountEnumMapper;
  private final PeriodEnumMapper periodEnumMapper;
  private final IncomeMapper incomeMapper;
  private final ExpenseMapper expenseMapper;

  @Autowired
  public BudgetMapper(AmountEnumMapper amountEnumMapper,
                      PeriodEnumMapper periodEnumMapper,
                      IncomeMapper incomeMapper,
                      ExpenseMapper expenseMapper) {
    this.amountEnumMapper = amountEnumMapper;
    this.periodEnumMapper = periodEnumMapper;
    this.incomeMapper = incomeMapper;
    this.expenseMapper = expenseMapper;
  }

  public Budget convertBudgetCommonRequestDtoToBudget(Long userId, BudgetCommonRequestDto budgetCommonRequestDto) {
    return new Budget(
      userId,
      budgetCommonRequestDto.getFinancialGoal(),
      periodEnumMapper.convert(budgetCommonRequestDto.getPeriod()),
      amountEnumMapper.convert(budgetCommonRequestDto.getCurrency())
    );
  }

  public BudgetCreateResponseDto convertBudgetToBudgetCreateResponseDto(Budget budget) {
    return new BudgetCreateResponseDto(
      budget.getId(),
      budget.getFinancialGoal(),
      budget.getUserId(),
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
      budget.getId(),
      budget.getFinancialGoal(),
      budget.getUserId(),
      periodEnumMapper.convert(budget.getPeriod()),
      amountEnumMapper.convert(budget.getBalance()),
      incomeMapper.convertIncomeListToIncomeCommonResponseDtoList(budget.getIncomes(), 0, budget.getIncomes().size()),
      expenseMapper.convertExpenseListToExpenseCommonResponseDtoList(budget.getExpenses(), 0, budget.getExpenses().size()),
      incomeMapper.convertIncomePlanListToIncomePlanCommonResponseDtoList(budget.getIncomePlans(), 0, budget.getIncomePlans().size()),
      expenseMapper.convertExpensePlanListToExpensePlanCommonResponseDtoList(budget.getExpensePlans(), 0, budget.getExpensePlans().size())
    );
  }
}
