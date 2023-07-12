package com.finance.lib.budget.domain.entity;

import com.finance.lib.budget.domain.entity.amount.Amount;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

@Data
@MappedSuperclass
public class Plan extends DependentByUserEntity {
  @NotNull
  @Embedded
  private Amount limit;

  @NotNull
  @Embedded
  private Period period;

  public Plan(Long userId,
              Amount limit) {
    super(userId);
    this.limit = limit;
    this.period = new Period(Duration.MONTH);
  }

  public Plan(Long userId, Amount limit, Period period) {
    super(userId);
    this.limit = limit;
    this.period = period;
  }

  public Plan() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Plan plan = (Plan) o;
    return Objects.equals(limit, plan.limit) && period == plan.period;
  }

  @Override
  public int hashCode() {
    return Objects.hash(limit, period);
  }

  public static class Builder extends DependentByUserEntity.Builder {
    protected Amount limit;
    protected Duration duration;

    public Builder limit(Amount limit) {
      this.limit = limit;
      return this;
    }

    public Plan build() {
      return new Plan(userId, limit);
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
