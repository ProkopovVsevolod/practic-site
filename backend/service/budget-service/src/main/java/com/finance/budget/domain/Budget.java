package com.finance.budget.domain;

import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.amount.Currency;
import com.finance.budget.domain.operation.expense.Expense;
import com.finance.budget.domain.operation.expense.ExpensePlan;
import com.finance.budget.domain.operation.income.Income;
import com.finance.budget.domain.operation.income.IncomePlan;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
public class Budget extends DependentByUserEntity<Long> {
  private String financialGoal;

  @Enumerated(EnumType.STRING)
  private Period period;

  @Embedded
  private Amount balance;

  @OneToMany(fetch = FetchType.EAGER, cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumn(name = "budget_id")
  private List<Income> incomes = new ArrayList<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumn(name = "budget_id")
  private List<Expense> expenses = new ArrayList<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumn(name = "budget_id")
  private List<IncomePlan> incomePlans = new ArrayList<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumn(name = "budget_id")
  private List<ExpensePlan> expensePlans = new ArrayList<>();

  public Budget(Long userId, String financialGoal, Period period, Currency balanceCurrency) {
    super(userId);
    this.financialGoal = financialGoal;
    this.period = period;
    balance = new Amount();
    balance.setAmount(new BigDecimal(0));
    balance.setAmountCurrency(balanceCurrency);
  }

  public Budget(Long userId,
                String financialGoal,
                Period period,
                Currency balanceCurrency,
                List<IncomePlan> incomePlans,
                List<ExpensePlan> expensePlans) {
    this(userId, financialGoal, period, balanceCurrency);
    this.incomePlans = incomePlans;
    this.expensePlans = expensePlans;
  }

  public Budget(Long userId,
                String financialGoal,
                Period period,
                Amount balance,
                List<IncomePlan> incomePlans,
                List<ExpensePlan> expensePlans) {
    super(userId);
    this.financialGoal = financialGoal;
    this.period = period;
    this.balance = balance;
    this.incomePlans = incomePlans;
    this.expensePlans = expensePlans;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Budget budget = (Budget) o;
    return Objects.equals(financialGoal, budget.financialGoal) && period == budget.period && Objects.equals(balance, budget.balance) && Objects.equals(incomes, budget.incomes) && Objects.equals(expenses, budget.expenses) && Objects.equals(incomePlans, budget.incomePlans) && Objects.equals(expensePlans, budget.expensePlans);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), financialGoal, period, balance, incomes, expenses, incomePlans, expensePlans);
  }
}
