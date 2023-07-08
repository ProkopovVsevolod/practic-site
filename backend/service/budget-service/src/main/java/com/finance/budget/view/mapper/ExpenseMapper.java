package com.finance.budget.view.mapper;

import com.finance.budget.domain.operation.expense.Expense;
import com.finance.budget.domain.operation.expense.ExpenseCategory;
import com.finance.budget.domain.operation.expense.ExpensePlan;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.expense.*;
import com.finance.budget.view.mapper.enums.AmountEnumMapper;
import com.finance.budget.view.mapper.enums.ExpenseEnumMapper;
import com.finance.budget.view.mapper.enums.PeriodEnumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseMapper {
  private final ExpenseEnumMapper expenseEnumMapper;
  private final AmountEnumMapper amountEnumMapper;
  private final PeriodEnumMapper periodEnumMapper;

  @Autowired
  public ExpenseMapper(ExpenseEnumMapper expenseEnumMapper,
                       AmountEnumMapper amountEnumMapper,
                       PeriodEnumMapper periodEnumMapper) {
    this.expenseEnumMapper = expenseEnumMapper;
    this.amountEnumMapper = amountEnumMapper;
    this.periodEnumMapper = periodEnumMapper;
  }

  public ListDto<ExpenseCommonResponseDto> convertExpenseListToExpenseCommonResponseDtoList(List<Expense> expenseList,
                                                                                            Integer offset,
                                                                                            Integer diapason) {
    List<ExpenseCommonResponseDto> expenseCommonResponseDtos = expenseList.stream()
      .map(this::convertExpenseToExpenseCommonResponseDto)
      .toList();
    return new ListDto<>(expenseCommonResponseDtos, offset, diapason);
  }

  public ExpenseCommonResponseDto convertExpenseToExpenseCommonResponseDto(Expense expense) {
    return new ExpenseCommonResponseDto(
      expense.getId(),
      expense.getName(),
      expense.getDescription(),
      expenseEnumMapper.convert(expense.getPaymentMethod()),
      expense.getDateTime(),
      expenseEnumMapper.convert(expense.getExpenseCategory()),
      amountEnumMapper.convert(expense.getAmount())
    );
  }

  public ListDto<ExpensePlanCommonResponseDto> convertExpensePlanListToExpensePlanCommonResponseDtoList(List<ExpensePlan> expensePlanList,
                                                                                                        Integer offset,
                                                                                                        Integer diapason) {
    List<ExpensePlanCommonResponseDto> expensePlanCommonResponseDtos = expensePlanList.stream()
      .map(this::convertExpensePlanToExpensePlanCommonResponseDto)
      .toList();
    return new ListDto<>(expensePlanCommonResponseDtos, offset, diapason);
  }

  public ExpensePlanCommonResponseDto convertExpensePlanToExpensePlanCommonResponseDto(ExpensePlan expensePlan) {
    return new ExpensePlanCommonResponseDto(
      expensePlan.getId(),
      amountEnumMapper.convert(expensePlan.getLimit()),
      periodEnumMapper.convert(expensePlan.getPeriod()),
      convertExpenseCategoryListToExpenseCategoryDtoList(expensePlan.getCategories(), 0, expensePlan.getCategories().size())
    );
  }

  public ListDto<ExpenseCategoryDto> convertExpenseCategoryListToExpenseCategoryDtoList(List<ExpenseCategory> expenseCategoryList,
                                                                                        Integer offset,
                                                                                        Integer diapason) {
    List<ExpenseCategoryDto> expenseCategoryDtos = expenseCategoryList.stream()
      .map(expenseEnumMapper::convert)
      .toList();
    return new ListDto<>(expenseCategoryDtos, offset, diapason);
  }

  public Expense convertExpenseCommonRequestDtoToExpense(Long userId, ExpenseCommonRequestDto expenseCommonRequestDto) {
    return new Expense(
      userId,
      expenseCommonRequestDto.getName(),
      expenseCommonRequestDto.getDescription(),
      amountEnumMapper.convert(expenseCommonRequestDto.getAmount()),
      expenseEnumMapper.convert(expenseCommonRequestDto.getPaymentMethod()),
      expenseEnumMapper.convert(expenseCommonRequestDto.getExpenseCategory())
    );
  }

  public List<ExpensePlan> convertExpensePlanCommonRequestDtoListToExpensePlanList(List<ExpensePlanCommonRequestDto> expensePlanCommonRequestDtos) {
    return expensePlanCommonRequestDtos.stream()
      .map(this::convertExpensePlanCommonRequestDtoToExpensePlan)
      .toList();
  }

  public ExpensePlan convertExpensePlanCommonRequestDtoToExpensePlan(ExpensePlanCommonRequestDto expensePlanCommonRequestDto) {
    return new ExpensePlan(
      amountEnumMapper.convert(expensePlanCommonRequestDto.getLimit()),
      periodEnumMapper.convert(expensePlanCommonRequestDto.getPeriod()),
      expensePlanCommonRequestDto.getCategories().stream().map(expenseEnumMapper::convert).toList()
    );
  }
}
