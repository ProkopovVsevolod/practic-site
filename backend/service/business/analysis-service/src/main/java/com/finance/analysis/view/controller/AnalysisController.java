package com.finance.analysis.view.controller;

import com.finance.analysis.service.AmountAnalysisService;
import com.finance.analysis.service.OperationAnalysisService;
import com.finance.analysis.view.dto.PlanActualCompareDto;
import com.finance.analysis.view.mapper.OperationMapper;
import com.finance.jwt.domain.OpenAccessToken;
import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.Period;
import com.finance.lib.budget.dto.DurationDto;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.OperationCommonResponseDto;
import com.finance.lib.budget.dto.PeriodDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import com.finance.lib.budget.mapper.AmountMapper;
import com.finance.lib.budget.mapper.PeriodMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
public class AnalysisController {
  private final OperationMapper operationMapper;
  private final PeriodMapper periodMapper;
  private final AmountMapper amountMapper;
  private final OperationAnalysisService operationAnalysisService;
  private final AmountAnalysisService amountAnalysisService;

  //TODO
  @GetMapping("/api/v1/analysis/budget/{budget-id}/overview")
  public void a(@PathVariable("budget-id") Long budgetId) {
    throw new NotImplementedException();
  }

  @GetMapping("/api/v1/analysis/budgets/{budget-id}/operations/{year}-{month}:{duration}")
  public ListDto<OperationCommonResponseDto> operationsByPeriod(@PathVariable("budget-id") Long budgetId,
                                                                @PathVariable("year") int year,
                                                                @PathVariable("month") int month,
                                                                @PathVariable("duration") String duration,
                                                                OpenAccessToken openAccessToken) {
    PeriodDto periodDto = new PeriodDto(year, month, DurationDto.createByName(duration));
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
    Period period = periodMapper.convert(periodDto);
    return operationMapper.convertList(operationAnalysisService.operationsByPeriod(compositeId, period));
  }

  //TODO
  @GetMapping("/api/v1/analysis/budgets/{budget-id}/balance")
  public AmountDto balance(@PathVariable("budget-id") Long budgetId,
                           OpenAccessToken openAccessToken) {
//    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
//    return amountMapper.convert(amountAnalysisService.calcBalance(compositeId));
    throw new NotImplementedException();
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

  //TODO
  @GetMapping("/api/v1/analysis/budgets/{budget-id}/planned-by-actual-incomes/{year}-{month}")
  public PlanActualCompareDto plannedByActualIncomes(@PathVariable("budget-id") Long budgetId,
                                                     @PathVariable("year") int year,
                                                     @PathVariable("month") int month,
                                                     OpenAccessToken openAccessToken) {
//    PeriodDto periodDto = new PeriodDto(year, month);
//    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
//    Period monthPeriod = periodMapper.convert(periodDto);
//    Amount diff = amountAnalysisService.incomeActualPlanDiff(compositeId, monthPeriod);
//    operationMapper.convert(diff,)
    throw new NotImplementedException();
  }

  //TODO
  @GetMapping("/api/v1/analysis/budgets/{budget-id}/planned-by-actual-expenses/{year}-{month}")
  public AmountDto plannedByActualExpenses(@PathVariable("budget-id") Long budgetId,
                                           @PathVariable("year") int year,
                                           @PathVariable("month") int month,
                                           OpenAccessToken openAccessToken) {
//    PeriodDto periodDto = new PeriodDto(year, month);
//    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
//    Period monthPeriod = periodMapper.convert(periodDto);
//    return amountMapper.convert(amountAnalysisService.expenseActualPlanDiff(compositeId, monthPeriod));
    throw new NotImplementedException();
  }
}