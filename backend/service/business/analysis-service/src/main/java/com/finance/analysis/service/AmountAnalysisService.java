package com.finance.analysis.service;

import com.finance.analysis.infrastructure.external.BudgetServiceClient;
import com.finance.jwt.domain.OpenAccessToken;
import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.Operation;
import com.finance.lib.budget.domain.entity.Period;
import com.finance.lib.budget.domain.entity.amount.Amount;
import com.finance.lib.budget.domain.entity.amount.Currency;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class AmountAnalysisService {
  private final BudgetServiceClient budgetServiceClient;
  private final OperationAnalysisService operationAnalysisService;

//TODO
  public Amount calcBalance(@Valid @NotNull CompositeId compositeId) {
//    Budget budget = budgetRepository.findById(compositeId)
//      .orElseThrow(() -> new IllegalArgumentException("Budget not found by id: " + compositeId));
//    return budget.getBalance();
    throw new NotImplementedException();
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

  //TODO
  public Amount incomeActualPlanDiff(CompositeId compositeId, Period period) {
//    List<Income> incomes = operationAnalysisService.incomesByPeriod(compositeId, period);
//    IncomePlan incomePlan = operationAnalysisService.incomePlanByPeriod(compositeId, period);
//    Amount actual = incomes.stream()
//      .map(Operation::getAmount)
//      .reduce(Amount.empty(incomePlan.getLimit().getAmountCurrency()), Amount::increment);
//    return incomePlan.getLimit().decrement(actual);
    throw new NotImplementedException();
  }

  //TODO
  public Amount expenseActualPlanDiff(CompositeId compositeId, Period period) {
//    List<Expense> expenses = operationAnalysisService.expensesByPeriod(compositeId, period);
//    ExpensePlan expensePlan = operationAnalysisService.expensePlanByPeriod(compositeId, period);
//    Amount actual = expenses.stream()
//      .map(Operation::getAmount)
//      .reduce(Amount.empty(expensePlan.getLimit().getAmountCurrency()), Amount::increment);
//    return expensePlan.getLimit().decrement(actual);
    throw new NotImplementedException();
  }
}
