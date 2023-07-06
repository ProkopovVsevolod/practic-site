package com.finance.budget.domain.expense;

import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.Period;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ExpensePlan {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_plan_id_sequence")
  @SequenceGenerator(name = "expense_plan_id_sequence", allocationSize = 10)
  private Long id;

  @Embedded
  private Amount limit;

  @Enumerated(EnumType.STRING)
  private Period period;

  @ElementCollection
  @Enumerated(EnumType.STRING)
  private List<ExpenseCategory> categories;
}
