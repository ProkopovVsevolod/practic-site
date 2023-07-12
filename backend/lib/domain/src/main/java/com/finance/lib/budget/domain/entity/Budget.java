package com.finance.lib.budget.domain.entity;

import com.finance.lib.budget.domain.entity.amount.Amount;
import com.finance.lib.budget.domain.entity.operation.expense.Expense;
import com.finance.lib.budget.domain.entity.operation.expense.ExpensePlan;
import com.finance.lib.budget.domain.entity.operation.income.Income;
import com.finance.lib.budget.domain.entity.operation.income.IncomePlan;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "budget")
public class Budget extends DependentByUserEntity {

  @Column(name = "financial_goal")
  private String financialGoal = "Unnamed";

  @NotNull
  @Embedded
  private Period period;

  @NotNull
  @Embedded
  private Amount balance;

  @OneToMany(fetch = FetchType.EAGER, cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumns({
    @JoinColumn(name = "budget_id", referencedColumnName = "id"),
    @JoinColumn(name = "budget_user_id", referencedColumnName = "user_Id")
  })
  private Set<Income> incomes;

  @OneToMany(fetch = FetchType.EAGER, cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumns({
    @JoinColumn(name = "budget_id", referencedColumnName = "id"),
    @JoinColumn(name = "budget_user_id", referencedColumnName = "user_id")
  })
  private Set<Expense> expenses;

  @OneToMany(fetch = FetchType.EAGER, cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumns({
    @JoinColumn(name = "budget_id", referencedColumnName = "id"),
    @JoinColumn(name = "budget_user_id", referencedColumnName = "user_id")
  })
  private Set<IncomePlan> incomePlans;

  @OneToMany(fetch = FetchType.EAGER, cascade = {
    CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH
  })
  @JoinColumns({
    @JoinColumn(name = "budget_id", referencedColumnName = "id"),
    @JoinColumn(name = "budget_user_id", referencedColumnName = "user_id")
  })
  private Set<ExpensePlan> expensePlans;

  public Budget(Long userId, String financialGoal, Period period, Amount balance) {
    super(userId);
    this.financialGoal = financialGoal;
    this.period = period;
    this.balance = balance;
  }

  public Budget() {
  }

  public Budget(Long userId,
                String financialGoal,
                Period period,
                Amount balance,
                Set<Income> incomes,
                Set<Expense> expenses,
                Set<IncomePlan> incomePlans,
                Set<ExpensePlan> expensePlans) {
    super(userId);
    this.financialGoal = financialGoal;
    this.period = period;
    this.balance = balance;
    this.incomes = incomes;
    this.expenses = expenses;
    this.incomePlans = incomePlans;
    this.expensePlans = expensePlans;
  }

  public static class Builder extends DependentByUserEntity.Builder {
    protected String financialGoal;
    protected Period period;
    protected Amount balance;
    protected Set<Income> incomes;
    protected Set<Expense> expenses;
    protected Set<IncomePlan> incomePlans;
    protected Set<ExpensePlan> expensePlans;

    public Builder financialGoal(String financialGoal) {
      this.financialGoal = financialGoal;
      return this;
    }

    public Builder period(Period period) {
      this.period = period;
      return this;
    }

    public Builder balance(Amount balance) {
      this.balance = balance;
      return this;
    }

    public Builder incomes(Set<Income> incomes) {
      this.incomes = incomes;
      return this;
    }

    public Builder expenses(Set<Expense> expenses) {
      this.expenses = expenses;
      return this;
    }

    public Builder incomePlans(Set<IncomePlan> incomePlans) {
      this.incomePlans = incomePlans;
      return this;
    }

    public Builder expensePlans(Set<ExpensePlan> expensePlans) {
      this.expensePlans = expensePlans;
      return this;
    }

    public Budget build() {
      return new Budget(
        userId,
        financialGoal,
        period,
        balance,
        incomes,
        expenses,
        incomePlans,
        expensePlans
      );
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
    Budget budget = (Budget) o;
    return Objects.equals(financialGoal, budget.financialGoal) && period == budget.period && Objects.equals(balance, budget.balance) && Objects.equals(incomes, budget.incomes) && Objects.equals(expenses, budget.expenses) && Objects.equals(incomePlans, budget.incomePlans) && Objects.equals(expensePlans, budget.expensePlans);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), financialGoal, period, balance, incomes, expenses, incomePlans, expensePlans);
  }

  public void addIncome(Income income) {
    if (!incomes.contains(income)) {
      balance = balance.increment(income.getAmount());
      incomes.add(income);
    }

  }

  public void addExpense(Expense expense) {
    if (!expenses.contains(expense)) {
      balance = balance.decrement(expense.getAmount());
      expenses.add(expense);
    }
  }

  public void addIncomePlan(IncomePlan incomePlan) {
    incomePlans.add(incomePlan);
  }

  public void addExpensePlan(ExpensePlan expensePlan) {
    expensePlans.add(expensePlan);
  }
}
