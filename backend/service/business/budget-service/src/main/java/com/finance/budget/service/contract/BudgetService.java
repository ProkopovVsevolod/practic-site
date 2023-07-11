package com.finance.budget.service.contract;

import com.finance.budget.domain.Budget;
import com.finance.budget.domain.CompositeId;

public interface BudgetService extends CrudService<Budget> {
  Budget addIncome(CompositeId compositeId, Long incomeId);
  Budget addExpense(CompositeId compositeId, Long expenseId);
  Budget addIncomePlan(CompositeId compositeId, Long incomePlanId);
  Budget addExpensePlan(CompositeId compositeId, Long expensePlanId);
}
