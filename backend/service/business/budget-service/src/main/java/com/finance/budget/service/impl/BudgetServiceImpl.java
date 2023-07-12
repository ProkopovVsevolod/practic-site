package com.finance.budget.service.impl;

import com.finance.budget.infrastructure.repository.contract.BudgetRepository;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import com.finance.budget.service.contract.*;
import com.finance.budget.validation.BudgetValidationService;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BudgetServiceImpl extends DependentByUserCrudService<Budget> implements BudgetService {
  private final BudgetRepository budgetRepository;
  private final BudgetValidationService budgetValidationService;
  private final IncomeService incomeService;
  private final ExpenseService expenseService;
  private final IncomePlanService incomePlanService;
  private final ExpensePlanService expensePlanService;

  public BudgetServiceImpl(BudgetRepository budgetRepository,
                           SessionCaller<Budget> sessionCaller,
                           IncomeService incomeService,
                           ExpenseService expenseService,
                           IncomePlanService incomePlanService,
                           ExpensePlanService expensePlanService,
                           BudgetValidationService budgetValidationService) {
    super(budgetRepository, sessionCaller);
    this.budgetRepository = budgetRepository;
    this.incomeService = incomeService;
    this.expenseService = expenseService;
    this.incomePlanService = incomePlanService;
    this.expensePlanService = expensePlanService;
    this.budgetValidationService = budgetValidationService;
  }

  @Override
  public Budget addIncome(@Valid @NotNull CompositeId id,
                          @NotNull Long incomeId) {
    budgetValidationService.shouldPresent(id);
    CompositeId incomeCompositeId = new CompositeId(incomeId, id.getUserId());
    Income income = incomeService.find(incomeCompositeId);
    Budget budget = find(id);
    budget.addIncome(income);
    return budget;
  }

  @Override
  public Budget addExpense(@Valid @NotNull CompositeId id,
                           @NotNull Long expenseId) {
    budgetValidationService.shouldPresent(id);
    CompositeId expenseCompositeId = new CompositeId(expenseId, id.getUserId());
    Expense expense = expenseService.find(expenseCompositeId);
    Budget budget = find(id);
    budget.addExpense(expense);
    return budget;
  }

  @Override
  public Budget addIncomePlan(@Valid @NotNull CompositeId id,
                              @NotNull Long incomePlanId) {
    budgetValidationService.shouldPresent(id);
    CompositeId incomePlanCompositeId = new CompositeId(incomePlanId, id.getUserId());
    IncomePlan incomePlan = incomePlanService.find(incomePlanCompositeId);
    Budget budget = find(id);
    budget.addIncomePlan(incomePlan);
    return budget;
  }

  @Override
  public Budget addExpensePlan(@Valid @NotNull CompositeId id,
                               @NotNull Long expensePlanId) {
    budgetValidationService.shouldPresent(id);
    CompositeId expensePlanCompositeId = new CompositeId(expensePlanId, id.getUserId());
    ExpensePlan expensePlan = expensePlanService.find(expensePlanCompositeId);
    Budget budget = find(id);
    budget.addExpensePlan(expensePlan);
    return budget;
  }

  @Override
  public Currency getBudgetCurrency(CompositeId compositeId) {
    budgetValidationService.shouldPresent(compositeId);
    return budgetRepository.getCurrency(compositeId);
  }

  @Override
  public List<Income> getBudgetIncomesByPeriod(CompositeId compositeId, Period period) {
    budgetValidationService.shouldPresent(compositeId);
    return budgetRepository.incomesByPeriod(compositeId, period.startDateTime(), period.endDateTime());
  }

  @Override
  public List<Expense> getBudgetExpensesByPeriod(CompositeId compositeId, Period period) {
    budgetValidationService.shouldPresent(compositeId);
    return budgetRepository.expensesByPeriod(compositeId, period.startDateTime(), period.endDateTime());
  }

  @Override
  public Amount getBalance(CompositeId compositeId) {
    Budget budget = budgetRepository.findById(compositeId)
      .orElseThrow(() -> new IllegalArgumentException("Budget with id: " + compositeId + " not found!"));
    return budget.getBalance();
  }

  @Override
  public IncomePlan getBudgetIncomePlans(@Valid @NotNull CompositeId id,
                                         @Valid @NotNull Period period,
                                         @Valid @NotNull IncomeCategory category) {
    return budgetRepository.getIncomePlans(id).stream()
      .filter(ip -> ip.getPeriod().equals(period))
      .filter(ip -> ip.getCategory().equals(category))
      .findAny()
      .orElseThrow(() -> new IllegalArgumentException("Cannot find income plan by period " + period + " and category " + category));
  }

  @Override
  public ExpensePlan getBudgetExpensePlans(@Valid @NotNull CompositeId id,
                                           @Valid @NotNull Period period,
                                           @Valid @NotNull ExpenseCategory category) {
    return budgetRepository.getExpensePlans(id).stream()
      .filter(ip -> ip.getPeriod().equals(period))
      .filter(ip -> ip.getCategory().equals(category))
      .findAny()
      .orElseThrow(() -> new IllegalArgumentException("Cannot find expense plan by period " + period + " and category " + category));
  }
}
