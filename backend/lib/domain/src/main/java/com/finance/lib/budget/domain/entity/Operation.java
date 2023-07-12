package com.finance.lib.budget.domain.entity;

import com.finance.lib.budget.domain.entity.amount.Amount;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Objects;

@Data
@MappedSuperclass
public abstract class Operation extends DependentByUserEntity implements Comparable<Operation> {
  @Column(name = "name", nullable = false)
  private String name = "Unnamed";

  @Column(name = "description", columnDefinition = "text")
  private String description;

  @NotNull
  @Column(name = "date_time", columnDefinition = "timestamp with time zone")
  private OffsetDateTime dateTime;

  @NotNull
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

  public Operation() {
  }

  public static class Builder extends DependentByUserEntity.Builder {
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

  @Override
  public int compareTo(Operation o) {
    if (dateTime.equals(o.dateTime)) {
      if (name.equals(o.name)) {
        if (amount.equals(o.amount)) {
          return description.compareTo(o.description);
        } else {
          return amount.compareTo(o.amount);
        }
      } else {
        return name.compareTo(o.name);
      }
    } else {
      return dateTime.compareTo(o.dateTime);
    }
  }

  public String getType() {
    return getClass().getSimpleName().toLowerCase();
  }
}
