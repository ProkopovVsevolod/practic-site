package com.finance.budget.domain.operation.expense;

import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.operation.Operation;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
public class Expense extends Operation {
  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  private ExpenseCategory expenseCategory;

  public Expense(Long userId,
                 String name,
                 String description,
                 Amount amount,
                 PaymentMethod paymentMethod,
                 ExpenseCategory expenseCategory) {
    super(userId, name, description, amount);
    this.paymentMethod = paymentMethod;
    this.expenseCategory = expenseCategory;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Expense expense = (Expense) o;
    return paymentMethod == expense.paymentMethod && expenseCategory == expense.expenseCategory;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), paymentMethod, expenseCategory);
  }
}
