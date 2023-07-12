package com.finance.lib.budget.domain.entity.operation.expense;

import com.finance.lib.budget.domain.entity.Operation;
import com.finance.lib.budget.domain.entity.amount.Amount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
public class Expense extends Operation {
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "payment_method")
  private PaymentMethod paymentMethod;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "expense_category")
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

  public Expense() {
  }

  public static class Builder extends Operation.Builder {
    private PaymentMethod paymentMethod;
    private ExpenseCategory expenseCategory;

    public Builder paymentMethod(PaymentMethod paymentMethod) {
      this.paymentMethod = paymentMethod;
      return this;
    }

    public Builder expenseCategory(ExpenseCategory expenseCategory) {
      this.expenseCategory = expenseCategory;
      return this;
    }

    public Expense build() {
      return new Expense(userId, name, description, amount, paymentMethod, expenseCategory);
    }
  }

  public static Builder builder() {
    return new Builder();
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
