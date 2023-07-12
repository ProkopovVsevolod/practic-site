package com.finance.lib.budget.mapper;

import com.finance.lib.budget.domain.entity.operation.expense.Expense;
import com.finance.lib.budget.domain.entity.operation.expense.ExpenseCategory;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.operation.expense.ExpenseCategoryDto;
import com.finance.lib.budget.dto.operation.expense.ExpenseCommonRequestDto;
import com.finance.lib.budget.dto.operation.expense.ExpenseCommonResponseDto;
import com.finance.lib.budget.mapper.enums.ExpenseEnumMapper;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ExpenseMapper {
  private final IdMapper idMapper;
  private final ExpenseEnumMapper expenseEnumMapper;
  private final AmountMapper amountMapper;

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
      Expense.class.getSimpleName(),
      idMapper.convert(expense.getCompositeId()),
      expense.getName(),
      expense.getDescription(),
      expense.getDateTime(),
      amountMapper.convert(expense.getAmount()),
      expenseEnumMapper.convert(expense.getPaymentMethod()),
      expenseEnumMapper.convert(expense.getExpenseCategory())
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
      amountMapper.convert(expenseCommonRequestDto.getAmount()),
      expenseEnumMapper.convert(expenseCommonRequestDto.getPaymentMethod()),
      expenseEnumMapper.convert(expenseCommonRequestDto.getExpenseCategory())
    );
  }

  public List<Expense> convertListDtoToList(ListDto<ExpenseCommonResponseDto> listDto) {
    return listDto.getElements().stream()
      .map(this::convertFromResponse)
      .toList();
  }

  public Expense convertFromResponse(ExpenseCommonResponseDto dto) {
    return new Expense(
      dto.getCompositeId().getUserId(),
      dto.getName(),
      dto.getDescription(),
      amountMapper.convert(dto.getAmount()),
      expenseEnumMapper.convert(dto.getPaymentMethod()),
      expenseEnumMapper.convert(dto.getExpenseCategory())
    );
  }
}
