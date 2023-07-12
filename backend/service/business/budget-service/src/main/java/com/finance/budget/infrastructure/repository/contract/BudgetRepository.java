package com.finance.budget.infrastructure.repository.contract;

import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;
import com.finance.lib.budget.domain.entity.Budget;
import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.amount.Currency;
import com.finance.lib.budget.domain.entity.operation.expense.Expense;
import com.finance.lib.budget.domain.entity.operation.expense.ExpensePlan;
import com.finance.lib.budget.domain.entity.operation.income.Income;
import com.finance.lib.budget.domain.entity.operation.income.IncomePlan;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;

public interface BudgetRepository extends DependentByUserRepository<Budget> {
  @Query("select b.balance.amountCurrency from Budget b where b.compositeId = :compositeId")
  Currency getCurrency(CompositeId compositeId);

  @Query("select i from Budget b join b.incomes i " +
    "where b.compositeId = :compositeId " +
    "and i.dateTime > :start and i.dateTime < :end")
  List<Income> incomesByPeriod(CompositeId compositeId, OffsetDateTime start, OffsetDateTime end);

  @Query("select e from Budget b join b.expenses e " +
    "where b.compositeId = :compositeId " +
    "and e.dateTime > :start and e.dateTime < :end")
  List<Expense> expensesByPeriod(CompositeId compositeId, OffsetDateTime start, OffsetDateTime end);

  @Query("select b.incomePlans from Budget b where b.compositeId = :compositeId")
  List<IncomePlan> getIncomePlans(CompositeId compositeId);

  @Query("select b.expensePlans from Budget b where b.compositeId = :compositeId")
  List<ExpensePlan> getExpensePlans(CompositeId compositeId);
}