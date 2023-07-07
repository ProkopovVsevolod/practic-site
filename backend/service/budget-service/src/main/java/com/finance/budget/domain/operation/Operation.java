package com.finance.budget.domain.operation;

import com.finance.budget.domain.DependentByUserEntity;
import com.finance.budget.domain.amount.Amount;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Objects;

@Data
@MappedSuperclass
@NoArgsConstructor
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
