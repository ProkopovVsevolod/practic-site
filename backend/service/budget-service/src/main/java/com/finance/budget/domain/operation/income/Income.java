package com.finance.budget.domain.operation.income;

import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.operation.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Objects;

@Data
@Entity
public class Income extends Operation {
  @Column(nullable = false)
  private String source;

  @Enumerated(EnumType.STRING)
  private IncomeCategory incomeCategory;

  public Income(Long userId,
                String name,
                String description,
                Amount amount,
                String source,
                IncomeCategory incomeCategory) {
    super(userId, name, description, amount);
    this.source = source;
    this.incomeCategory = incomeCategory;
  }

  public Income() {
  }

  public Income(Long userId,
                String name,
                String description,
                OffsetDateTime dateTime,
                Amount amount,
                String source,
                IncomeCategory incomeCategory) {
    super(userId, name, description, dateTime, amount);
    this.source = source;
    this.incomeCategory = incomeCategory;
  }

  public static class Builder extends Operation.Builder {
    protected String source;
    protected IncomeCategory incomeCategory;

    public Builder source(String source) {
      this.source = source;
      return this;
    }

    public Builder incomeCategory(IncomeCategory incomeCategory) {
      this.incomeCategory = incomeCategory;
      return this;
    }

    public Income build() {
      return new Income(userId, name, description, amount, source, incomeCategory);
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
    Income income = (Income) o;
    return Objects.equals(source, income.source) && incomeCategory == income.incomeCategory;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), source, incomeCategory);
  }
}
