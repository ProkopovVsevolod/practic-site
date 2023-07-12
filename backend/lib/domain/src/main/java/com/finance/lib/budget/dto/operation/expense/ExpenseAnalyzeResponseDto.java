package com.finance.lib.budget.dto.operation.expense;

import com.finance.lib.budget.dto.operation.OperationAnalyseResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ExpenseAnalyzeResponseDto extends OperationAnalyseResponseDto {
  private PaymentMethodDto paymentMethod;
  private ExpenseCategoryDto expenseCategory;
}
