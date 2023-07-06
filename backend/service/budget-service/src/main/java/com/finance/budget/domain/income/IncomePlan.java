package com.finance.budget.domain.income;

import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.Period;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class IncomePlan {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "income_plan_id_sequence")
  @SequenceGenerator(name = "income_plan_id_sequence", allocationSize = 10)
  private Long id;

  @Embedded
  private Amount limit;

  @Enumerated(EnumType.STRING)
  private Period period;

  @ElementCollection
  @Enumerated(EnumType.STRING)
  private List<IncomeCategory> categories;
}
