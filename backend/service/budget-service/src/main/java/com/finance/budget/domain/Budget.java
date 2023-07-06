package com.finance.budget.domain;

import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.expense.Expense;
import com.finance.budget.domain.expense.ExpensePlan;
import com.finance.budget.domain.income.Income;
import com.finance.budget.domain.income.IncomePlan;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Budget {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_id_sequence")
  @SequenceGenerator(name = "budget_id_sequence", allocationSize = 10)
  private Long id;

  private String financialGoal;

  @Enumerated(EnumType.STRING)
  private Period period;

  @Embedded
  private Amount amount;

  @OneToMany(cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumn(name = "budget_id")
  private List<Income> incomes;

  @OneToMany(cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumn(name = "budget_id")
  private List<Expense> expenses;

  @OneToMany(cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumn(name = "budget_id")
  private List<IncomePlan> incomePlans;

  @OneToMany(cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumn(name = "budget_id")
  private List<ExpensePlan> expensePlans;
}
