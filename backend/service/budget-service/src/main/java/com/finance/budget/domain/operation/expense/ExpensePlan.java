package com.finance.budget.domain.operation.expense;

import com.finance.budget.domain.Period;
import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.Plan;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
public class ExpensePlan extends Plan {
  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  @Column(name = "categories")
  private List<ExpenseCategory> categories;

  public ExpensePlan(Long userId,
                     Amount limit,
                     Period period,
                     List<ExpenseCategory> categories) {
    super(userId, limit, period);
    this.categories = categories;
  }

  public ExpensePlan() {}

  public static class Builder extends Plan.Builder {
    protected List<ExpenseCategory> categories;

    public Builder categories(List<ExpenseCategory> categories) {
      this.categories = categories;
      return this;
    }

    public ExpensePlan build() {
      return new ExpensePlan(userId, limit, period, categories);
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
    return Objects.equals(categories, that.categories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), categories);
  }
}
