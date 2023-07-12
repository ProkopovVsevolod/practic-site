package com.finance.lib.budget.dto.expense;

import com.finance.lib.budget.dto.amount.AmountDto;
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
