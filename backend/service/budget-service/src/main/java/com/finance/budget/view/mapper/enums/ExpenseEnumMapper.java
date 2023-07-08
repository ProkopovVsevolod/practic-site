package com.finance.budget.view.mapper.enums;

import com.finance.budget.domain.operation.expense.ExpenseCategory;
import com.finance.budget.domain.operation.expense.PaymentMethod;
import com.finance.budget.view.dto.expense.ExpenseCategoryDto;
import com.finance.budget.view.dto.expense.PaymentMethodDto;
import org.springframework.stereotype.Component;

@Component
public class ExpenseEnumMapper {

  public PaymentMethodDto convert(PaymentMethod paymentMethod) {
    return PaymentMethodDto.createByName(paymentMethod.getName());
  }

  public PaymentMethod convert(PaymentMethodDto paymentMethodDto) {
    return PaymentMethod.byName(paymentMethodDto.toName());
  }

  public ExpenseCategoryDto convert(ExpenseCategory expenseCategory) {
    return ExpenseCategoryDto.createByName(expenseCategory.getName());
  }

  public ExpenseCategory convert(ExpenseCategoryDto expenseCategoryDto) {
    return ExpenseCategory.byName(expenseCategoryDto.toName());
  }
}
