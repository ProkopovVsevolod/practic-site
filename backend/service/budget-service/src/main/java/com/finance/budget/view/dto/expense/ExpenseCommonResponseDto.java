package com.finance.budget.view.dto.expense;

import com.finance.budget.view.dto.amount.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCommonResponseDto {
  private Long id;
  private String name;
  private String description;
  private PaymentMethodDto paymentMethod;
  private OffsetDateTime dateTime;
  private ExpenseCategoryDto expenseCategory;
  private AmountDto amount;
}
