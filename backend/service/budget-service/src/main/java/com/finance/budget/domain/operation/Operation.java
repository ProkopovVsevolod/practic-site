package com.finance.budget.domain.operation;

import com.finance.budget.domain.DependentByUserEntity;
import com.finance.budget.domain.amount.Amount;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Objects;

@Data
@MappedSuperclass
public class Operation extends DependentByUserEntity<Long> {
  @Column(nullable = false)
  private String name;

  @Column(columnDefinition = "text")
  private String description;

  @Column(columnDefinition = "timestamp with time zone")
  private OffsetDateTime dateTime;

  @Embedded
  private Amount amount;

  public Operation(Long userId, String name, String description, Amount amount) {
    super(userId);
    this.name = name;
    this.description = description;
    this.amount = amount;
    this.dateTime = OffsetDateTime.now();
  }

  public Operation(Long userId, String name, String description, OffsetDateTime dateTime, Amount amount) {
    super(userId);
    this.name = name;
    this.description = description;
    this.dateTime = dateTime;
    this.amount = amount;
  }

  public Operation() {}

  public static class Builder extends DependentByUserEntity.Builder<Long>{
    protected String name;
    protected String description;
    protected OffsetDateTime dateTime;
    protected Amount amount;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder dateTime(OffsetDateTime dateTime) {
      this.dateTime = dateTime;
      return this;
    }

    public Builder amount(Amount amount) {
      this.amount = amount;
      return this;
    }

    public Operation build() {
      return new Operation(userId, name, description, dateTime, amount);
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
    Operation operation = (Operation) o;
    return Objects.equals(name, operation.name) && Objects.equals(description, operation.description) && Objects.equals(amount, operation.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, description, amount);
  }
}
