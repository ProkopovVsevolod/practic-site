package com.finance.budget.view.mapper.enums;

import com.finance.budget.domain.operation.expense.ExpenseCategory;
import com.finance.budget.domain.operation.expense.PaymentMethod;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.expense.ExpenseCategoryDto;
import com.finance.budget.view.dto.expense.PaymentMethodDto;

import java.util.ArrayList;
import java.util.List;

public class ExpenseEnumMapper {
  public PaymentMethodDto convert(PaymentMethod paymentMethod) {
    return PaymentMethodDto.createByName(paymentMethod.getName());
  }

  public PaymentMethod convert(PaymentMethodDto paymentMethodDto) {
    return PaymentMethod.byName(paymentMethodDto.toName());
  }

  public ListDto<ExpenseCategoryDto> convertToDtoList(List<ExpenseCategory> expenseCategoryList,
                                                      Integer offset,
                                                      Integer diapason) {
    List<ExpenseCategoryDto> expenseCategoryDtos = expenseCategoryList.stream()
      .map(this::convert)
      .toList();
    return new ListDto<>(expenseCategoryDtos, offset, diapason);
  }

  public ListDto<ExpenseCategoryDto> convertToDtoList(List<ExpenseCategory> expenseCategoryList) {
    List<ExpenseCategoryDto> expenseCategoryDtos = expenseCategoryList.stream()
      .map(this::convert)
      .toList();
    return new ListDto<>(expenseCategoryDtos);
  }

  public List<ExpenseCategory> convertToDomainList(List<ExpenseCategoryDto> expenseCategoryDtoList,
                                                   Integer offset,
                                                   Integer diapason) {
    return new ArrayList<>(expenseCategoryDtoList.stream()
      .map(this::convert)
      .toList());
  }

  public List<ExpenseCategory> convertToDomainList(List<ExpenseCategoryDto> expenseCategoryDtoList) {
    return new ArrayList<>(expenseCategoryDtoList.stream()
      .map(this::convert)
      .toList());
  }

  public ExpenseCategoryDto convert(ExpenseCategory expenseCategory) {
    return ExpenseCategoryDto.createByName(expenseCategory.getName());
  }

  public ExpenseCategory convert(ExpenseCategoryDto expenseCategoryDto) {
    return ExpenseCategory.byName(expenseCategoryDto.toName());
  }
}
