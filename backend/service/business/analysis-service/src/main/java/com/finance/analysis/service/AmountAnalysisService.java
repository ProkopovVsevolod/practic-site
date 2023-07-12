package com.finance.analysis.service;

import com.finance.analysis.infrastructure.external.BudgetServiceClient;
import com.finance.jwt.domain.OpenAccessToken;
import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.Operation;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AmountAnalysisService {
  private final BudgetServiceClient budgetServiceClient;
  private final OperationAnalysisService operationAnalysisService;

  public Amount calcBalance(@Valid @NotNull CompositeId compositeId,
                            OpenAccessToken openAccessToken) {
    return budgetServiceClient.getBalance(compositeId.getId(), openAccessToken);
  }

  public Amount calcDiffByPeriod(@Valid @NotNull CompositeId budgetCompositeId,
                                 @Valid @NotNull Period period,
                                 OpenAccessToken openAccessToken) {
    Currency currency = budgetServiceClient.getBudgetCurrency(budgetCompositeId.getId(), openAccessToken);
    Amount incomeAmount = budgetServiceClient.getBudgetIncomesByPeriod(budgetCompositeId.getId(), period, openAccessToken)
      .stream()
      .map(Operation::getAmount)
      .reduce(new Amount(new BigDecimal(0), currency), Amount::increment);
    Amount expenseAmount = budgetServiceClient.getBudgetExpensesByPeriod(budgetCompositeId.getId(), period, openAccessToken)
      .stream()
      .map(Operation::getAmount)
      .reduce(new Amount(new BigDecimal(0), currency), Amount::increment);
    return incomeAmount.decrement(expenseAmount);
  }

  public Amount incomeActualPlanDiffByCategory(CompositeId compositeId,
                                     Period period,
                                     IncomeCategory category,
                                     OpenAccessToken openAccessToken) {
    IncomePlan incomePlan = budgetServiceClient.getIncomePlan(compositeId.getId(), period, category, openAccessToken);
    List<Income> incomes = budgetServiceClient.getBudgetIncomesByPeriod(compositeId.getId(), period, openAccessToken);
    Amount actual = incomes.stream()
      .filter(income -> income.getIncomeCategory().equals(category))
      .map(Operation::getAmount)
      .reduce(Amount.empty(incomePlan.getLimit().getAmountCurrency()), Amount::increment);
    return incomePlan.getLimit().decrement(actual);
  }

  public Amount expenseActualPlanDiffByCategory(CompositeId compositeId,
                                      Period period,
                                      ExpenseCategory category,
                                      OpenAccessToken openAccessToken) {
    ExpensePlan expensePlan = budgetServiceClient.getExpensePlan(compositeId.getId(), period, category, openAccessToken);
    List<Expense> expenses = budgetServiceClient.getBudgetExpensesByPeriod(compositeId.getId(), period, openAccessToken);
    Amount actual = expenses.stream()
      .map(Operation::getAmount)
      .reduce(Amount.empty(expensePlan.getLimit().getAmountCurrency()), Amount::increment);
    return expensePlan.getLimit().decrement(actual);
  }
}
