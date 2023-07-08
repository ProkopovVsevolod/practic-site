package com.finance.budget.service.impl;

import com.finance.budget.domain.Budget;
import com.finance.budget.domain.CompositeId;
import com.finance.budget.domain.operation.expense.Expense;
import com.finance.budget.domain.operation.expense.ExpensePlan;
import com.finance.budget.domain.operation.income.Income;
import com.finance.budget.domain.operation.income.IncomePlan;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import com.finance.budget.service.contract.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BudgetServiceImpl extends DependentByUserCrudService<Budget> implements BudgetService {
  private final IncomeService incomeService;
  private final ExpenseService expenseService;
  private final IncomePlanService incomePlanService;
  private final ExpensePlanService expensePlanService;

  public BudgetServiceImpl(DependentByUserRepository<Budget> dependentByUserRepository,
                           SessionCaller<Budget> sessionCaller,
                           IncomeService incomeService,
                           ExpenseService expenseService,
                           IncomePlanService incomePlanService,
                           ExpensePlanService expensePlanService) {
    super(dependentByUserRepository, sessionCaller);
    this.incomeService = incomeService;
    this.expenseService = expenseService;
    this.incomePlanService = incomePlanService;
    this.expensePlanService = expensePlanService;
  }

  @Override
  public Budget addIncome(CompositeId id, Long incomeId) {
    Income income = incomeService.find(id);
    Budget budget = find(id);
    budget.addIncome(income);
    return budget;
  }

  @Override
  public Budget addExpense(CompositeId id, Long expenseId) {
    Expense expense = expenseService.find(id);
    Budget budget = find(id);
    budget.addExpense(expense);
    return budget;
  }

  @Override
  public Budget addIncomePlan(CompositeId id, Long incomePlanId) {
    IncomePlan incomePlan = incomePlanService.find(id);
    Budget budget = find(id);
    budget.addIncomePlan(incomePlan);
    return budget;
  }

  @Override
  public Budget addExpensePlan(CompositeId id, Long expensePlanId) {
    ExpensePlan expensePlan = expensePlanService.find(id);
    Budget budget = find(id);
    budget.addExpensePlan(expensePlan);
    return budget;
  }
}
