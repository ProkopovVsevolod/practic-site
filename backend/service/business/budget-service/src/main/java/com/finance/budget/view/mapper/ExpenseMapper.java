package com.finance.budget.view.mapper;

import com.finance.budget.domain.operation.expense.Expense;
import com.finance.budget.domain.operation.expense.ExpenseCategory;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.expense.ExpenseCategoryDto;
import com.finance.budget.view.dto.expense.ExpenseCommonRequestDto;
import com.finance.budget.view.dto.expense.ExpenseCommonResponseDto;
import com.finance.budget.view.mapper.enums.AmountEnumMapper;
import com.finance.budget.view.mapper.enums.ExpenseEnumMapper;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ExpenseMapper {
  private final IdMapper idMapper;
  private final ExpenseEnumMapper expenseEnumMapper;
  private final AmountEnumMapper amountEnumMapper;

  public ListDto<ExpenseCommonResponseDto> convertExpenseListToExpenseCommonResponseDtoList(Collection<Expense> expenseList,
                                                                                            Integer offset,
                                                                                            Integer diapason) {
    List<ExpenseCommonResponseDto> expenseCommonResponseDtos = expenseList.stream()
      .map(this::convertExpenseToExpenseCommonResponseDto)
      .toList();
    return new ListDto<>(expenseCommonResponseDtos, offset, diapason);
  }

  public ListDto<ExpenseCommonResponseDto> convertExpenseListToExpenseCommonResponseDtoList(Collection<Expense> expenseList) {
    List<ExpenseCommonResponseDto> expenseCommonResponseDtos = expenseList.stream()
      .map(this::convertExpenseToExpenseCommonResponseDto)
      .toList();
    return new ListDto<>(expenseCommonResponseDtos);
  }

  public ExpenseCommonResponseDto convertExpenseToExpenseCommonResponseDto(Expense expense) {
    return new ExpenseCommonResponseDto(
      idMapper.convert(expense.getCompositeId()),
      expense.getName(),
      expense.getDescription(),
      expenseEnumMapper.convert(expense.getPaymentMethod()),
      expense.getDateTime(),
      expenseEnumMapper.convert(expense.getExpenseCategory()),
      amountEnumMapper.convert(expense.getAmount())
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
}
