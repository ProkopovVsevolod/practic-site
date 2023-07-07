package com.finance.budget.domain.operation.income;

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
public class IncomePlan extends Plan {
  @ElementCollection
  @Enumerated(EnumType.STRING)
  private List<IncomeCategory> categories;

  public IncomePlan(Amount limit,
                    Period period,
                    List<IncomeCategory> categories) {
    super(limit, period);
    this.categories = categories;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    IncomePlan that = (IncomePlan) o;
    return Objects.equals(categories, that.categories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), categories);
  }
}
