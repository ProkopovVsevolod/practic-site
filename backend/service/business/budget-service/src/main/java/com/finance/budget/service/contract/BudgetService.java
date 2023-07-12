package com.finance.budget.service.contract;

import com.finance.lib.budget.domain.entity.Budget;
import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.Period;
import com.finance.lib.budget.domain.entity.amount.Amount;
import com.finance.lib.budget.domain.entity.amount.Currency;
import com.finance.lib.budget.domain.entity.operation.expense.Expense;
import com.finance.lib.budget.domain.entity.operation.expense.ExpenseCategory;
import com.finance.lib.budget.domain.entity.operation.expense.ExpensePlan;
import com.finance.lib.budget.domain.entity.operation.income.Income;
import com.finance.lib.budget.domain.entity.operation.income.IncomeCategory;
import com.finance.lib.budget.domain.entity.operation.income.IncomePlan;

import java.util.List;

public interface BudgetService extends CrudService<Budget> {
  Budget addIncome(CompositeId compositeId, Long incomeId);
  Budget addExpense(CompositeId compositeId, Long expenseId);
  Budget addIncomePlan(CompositeId compositeId, Long incomePlanId);
  Budget addExpensePlan(CompositeId compositeId, Long expensePlanId);
  Currency getBudgetCurrency(CompositeId compositeId);
  List<Income> getBudgetIncomesByPeriod(CompositeId compositeId, Period period);
  List<Expense> getBudgetExpensesByPeriod(CompositeId compositeId, Period period);
  Amount getBalance(CompositeId compositeId);
  IncomePlan getBudgetIncomePlans(CompositeId budgetCompositeId, Period period, IncomeCategory incomeCategory);
  ExpensePlan getBudgetExpensePlans(CompositeId budgetCompositeId, Period period, ExpenseCategory expenseCategory);
}
