package com.finance.lib.budget.domain.entity.operation.expense;

import com.finance.lib.budget.domain.entity.Plan;
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
public class ExpensePlan extends Plan {
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "categories")
  private ExpenseCategory category;

  public ExpensePlan(Long userId,
                     Amount limit,
                     ExpenseCategory category) {
    super(userId, limit);
    this.category = category;
  }

  public ExpensePlan() {}

  public static class Builder extends Plan.Builder {
    protected ExpenseCategory category;

    public Builder categories(ExpenseCategory category) {
      this.category = category;
      return this;
    }

    public ExpensePlan build() {
      return new ExpensePlan(userId, limit, category);
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
    ExpensePlan that = (ExpensePlan) o;
    return category == that.category;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), category);
  }
}
