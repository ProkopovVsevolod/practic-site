package com.finance.budget.domain.operation.expense;

import com.finance.budget.domain.Period;
import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.operation.Plan;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
public class ExpensePlan extends Plan {
  @ElementCollection
  @Enumerated(EnumType.STRING)
  private List<ExpenseCategory> categories;

  public ExpensePlan(Amount limit, Period period, List<ExpenseCategory> categories) {
    super(limit, period);
    this.categories = categories;
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
