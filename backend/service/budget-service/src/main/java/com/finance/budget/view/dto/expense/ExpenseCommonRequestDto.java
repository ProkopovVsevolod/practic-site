package com.finance.budget.view.dto.expense;

import com.finance.budget.view.dto.amount.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCommonRequestDto {
  private String name;
  private String description;
  private PaymentMethodDto paymentMethod;
  private ExpenseCategoryDto expenseCategory;
  private AmountDto amount;
}
