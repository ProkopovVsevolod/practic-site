package com.finance.analysis.view.controller;

import com.finance.analysis.infrastructure.external.BudgetServiceClient;
import com.finance.analysis.service.AmountAnalysisService;
import com.finance.analysis.service.OperationAnalysisService;
import com.finance.analysis.view.dto.PlanActualCompareDto;
import com.finance.analysis.view.mapper.OperationMapper;
import com.finance.jwt.domain.OpenAccessToken;
import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.Period;
import com.finance.lib.budget.domain.entity.amount.Amount;
import com.finance.lib.budget.domain.entity.operation.expense.Expense;
import com.finance.lib.budget.domain.entity.operation.expense.ExpenseCategory;
import com.finance.lib.budget.domain.entity.operation.expense.ExpensePlan;
import com.finance.lib.budget.domain.entity.operation.income.Income;
import com.finance.lib.budget.domain.entity.operation.income.IncomeCategory;
import com.finance.lib.budget.domain.entity.operation.income.IncomePlan;
import com.finance.lib.budget.dto.DurationDto;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.PeriodDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import com.finance.lib.budget.dto.operation.OperationAnalyseResponseDto;
import com.finance.lib.budget.dto.operation.expense.ExpenseCategoryDto;
import com.finance.lib.budget.dto.operation.income.IncomeCategoryDto;
import com.finance.lib.budget.mapper.AmountMapper;
import com.finance.lib.budget.mapper.PeriodMapper;
import com.finance.lib.budget.mapper.enums.ExpenseEnumMapper;
import com.finance.lib.budget.mapper.enums.IncomeEnumMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
public class AnalysisController {
  private final OperationMapper operationMapper;
  private final PeriodMapper periodMapper;
  private final AmountMapper amountMapper;
  private final IncomeEnumMapper incomeEnumMapper;
  private final ExpenseEnumMapper expenseEnumMapper;
  private final OperationAnalysisService operationAnalysisService;
  private final AmountAnalysisService amountAnalysisService;
  private final BudgetServiceClient budgetServiceClient;

  //TODO
  @GetMapping("/api/v1/analysis/budget/{budget-id}/overview")
  public void a(@PathVariable("budget-id") Long budgetId) {
    throw new NotImplementedException();
  }

  @GetMapping("/api/v1/analysis/budgets/{budget-id}/operations/{year}-{month}:{duration}")
  public ListDto<OperationAnalyseResponseDto> operationsByPeriod(@PathVariable("budget-id") Long budgetId,
                                                                 @PathVariable("year") int year,
                                                                 @PathVariable("month") int month,
                                                                 @PathVariable("duration") String duration,
                                                                 OpenAccessToken openAccessToken) {
    PeriodDto periodDto = new PeriodDto(year, month, DurationDto.createByName(duration));
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
    Period period = periodMapper.convert(periodDto);
    return operationMapper.convertList(operationAnalysisService.operationsByPeriod(compositeId, period, openAccessToken));
  }

  @GetMapping("/api/v1/analysis/budgets/{budget-id}/balance")
  public AmountDto balance(@PathVariable("budget-id") Long budgetId,
                           OpenAccessToken openAccessToken) {
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
    return amountMapper.convert(amountAnalysisService.calcBalance(compositeId, openAccessToken));
  }

  @GetMapping("/api/v1/analysis/budgets/{budget-id}/diff/{year}-{month}:{duration}")
  public AmountDto diffByPeriod(@PathVariable("budget-id") Long budgetId,
                                @PathVariable("year") int year,
                                @PathVariable("month") int month,
                                @PathVariable("duration") String duration,
                                OpenAccessToken openAccessToken) {
    PeriodDto periodDto = new PeriodDto(year, month, DurationDto.createByName(duration));
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
    Period period = periodMapper.convert(periodDto);
    return amountMapper.convert(amountAnalysisService.calcDiffByPeriod(compositeId, period, openAccessToken));
  }

  @GetMapping("/api/v1/analysis/budgets/{budget-id}/planned-by-actual-incomes/{year}-{month}/{category}")
  public PlanActualCompareDto plannedByActualIncomesDiff(@PathVariable("budget-id") Long budgetId,
                                              @PathVariable("year") int year,
                                              @PathVariable("month") int month,
                                              @PathVariable("category") String categoryName,
                                              OpenAccessToken openAccessToken) {
    PeriodDto periodDto = new PeriodDto(year, month);
    Period monthPeriod = periodMapper.convert(periodDto);
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
    IncomeCategoryDto categoryDto = IncomeCategoryDto.createByName(categoryName);
    IncomeCategory category = incomeEnumMapper.convert(categoryDto);

    Amount diff = amountAnalysisService.incomeActualPlanDiffByCategory(compositeId, monthPeriod, category, openAccessToken);
    IncomePlan incomePlan = budgetServiceClient.getIncomePlan(compositeId.getId(), monthPeriod, category, openAccessToken);
    List<Income> operations = budgetServiceClient.getBudgetIncomesByPeriod(compositeId.getId(), monthPeriod, openAccessToken);
    return operationMapper.convert(diff, incomePlan, operations);
  }

  @GetMapping("/api/v1/analysis/budgets/{budget-id}/planned-by-actual-expenses/{year}-{month}/{category}")
  public PlanActualCompareDto plannedByActualExpensesDiff(@PathVariable("budget-id") Long budgetId,
                                                          @PathVariable("year") int year,
                                                          @PathVariable("month") int month,
                                                          @PathVariable("category") String categoryName,
                                                          OpenAccessToken openAccessToken) {
    ExpenseCategoryDto categoryDto = ExpenseCategoryDto.createByName(categoryName);
    ExpenseCategory category = expenseEnumMapper.convert(categoryDto);
    PeriodDto periodDto = new PeriodDto(year, month);
    Period monthPeriod = periodMapper.convert(periodDto);
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());

    Amount diff = amountAnalysisService.expenseActualPlanDiffByCategory(compositeId, monthPeriod, category, openAccessToken);
    ExpensePlan expensePlan = budgetServiceClient.getExpensePlan(compositeId.getId(), monthPeriod, category, openAccessToken);
    List<Expense> operations = budgetServiceClient.getBudgetExpensesByPeriod(compositeId.getId(), monthPeriod, openAccessToken);
    return operationMapper.convert(diff, expensePlan, operations);
  }
}