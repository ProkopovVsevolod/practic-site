package com.finance.budget.domain.operation;

import com.finance.budget.domain.Period;
import com.finance.budget.domain.amount.Amount;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Plan {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_id_sequence")
  @SequenceGenerator(name = "plan_id_sequence", allocationSize = 10)
  private Long id;

  @Embedded
  private Amount limit;

  @Enumerated(EnumType.STRING)
  private Period period;

  public Plan(Amount limit, Period period) {
    this.limit = limit;
    this.period = period;
  }

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
}
