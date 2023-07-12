package com.finance.lib.budget.dto.operation.expense;

import com.finance.lib.budget.dto.CompositeIdDto;
import com.finance.lib.budget.dto.operation.OperationCommonResponseDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ExpenseCommonResponseDto extends OperationCommonResponseDto {
  private PaymentMethodDto paymentMethod;
  private ExpenseCategoryDto expenseCategory;

  public ExpenseCommonResponseDto(String type,
                                  CompositeIdDto compositeId,
                                  String name,
                                  String description,
                                  OffsetDateTime dateTime,
                                  AmountDto amount,
                                  PaymentMethodDto paymentMethod,
                                  ExpenseCategoryDto expenseCategory) {
    super(type, compositeId, name, description, dateTime, amount);
    this.paymentMethod = paymentMethod;
    this.expenseCategory = expenseCategory;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ExpenseCommonResponseDto that = (ExpenseCommonResponseDto) o;
    return paymentMethod == that.paymentMethod && expenseCategory == that.expenseCategory;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), paymentMethod, expenseCategory);
  }
}
