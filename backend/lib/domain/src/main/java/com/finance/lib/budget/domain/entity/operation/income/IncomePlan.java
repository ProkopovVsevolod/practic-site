package com.finance.lib.budget.domain.entity.operation.income;

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
public class IncomePlan extends Plan {
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "category")
  private IncomeCategory category;

  public IncomePlan(Long userId,
                    Amount limit,
                    IncomeCategory category) {
    super(userId, limit);
    this.category = category;
  }

  public IncomePlan() {
  }

  public static class Builder extends Plan.Builder {
    protected IncomeCategory category;

    public Builder category(IncomeCategory category) {
      this.category = category;
      return this;
    }

    public IncomePlan build() {
      return new IncomePlan(userId, limit, category);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    IncomePlan that = (IncomePlan) o;
    return category == that.category;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), category);
  }
}
