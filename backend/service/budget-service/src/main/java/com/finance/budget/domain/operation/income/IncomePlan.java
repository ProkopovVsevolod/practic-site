package com.finance.budget.domain.operation.income;

import com.finance.budget.domain.Period;
import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.Plan;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
public class IncomePlan extends Plan {
  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  @Column(name = "categories")
  private List<IncomeCategory> categories;

  public IncomePlan(Long userId,
                    Amount limit,
                    Period period,
                    List<IncomeCategory> categories) {
    super(userId, limit, period);
    this.categories = categories;
  }

  public IncomePlan() {
  }

  public static class Builder extends Plan.Builder {
    protected List<IncomeCategory> categories;

    public Builder categories(List<IncomeCategory> categories) {
      this.categories = categories;
      return this;
    }

    public IncomePlan build() {
      return new IncomePlan(userId, limit, period, categories);
    }
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
