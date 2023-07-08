package com.finance.budget.view.controller;

import com.finance.budget.domain.Budget;
import com.finance.budget.domain.CompositeId;
import com.finance.budget.service.contract.BudgetService;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.budget.BudgetCommonRequestDto;
import com.finance.budget.view.dto.budget.BudgetCommonResponseDto;
import com.finance.budget.view.dto.budget.BudgetCreateResponseDto;
import com.finance.budget.view.mapper.BudgetMapper;
import com.finance.jwt.domain.OpenAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
public class BudgetController {
  private final BudgetMapper budgetMapper;
  private final BudgetService budgetService;

  @Autowired
  public BudgetController(BudgetMapper budgetMapper,
                          BudgetService budgetService) {
    this.budgetMapper = budgetMapper;
    this.budgetService = budgetService;
  }

  @PostMapping("/api/v1/budgets")
  @ResponseStatus(HttpStatus.CREATED)
  public BudgetCreateResponseDto create(@RequestBody BudgetCommonRequestDto createDto,
                                        OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    Budget newBudget = budgetMapper.convertBudgetCommonRequestDtoToBudget(userId, createDto);
    Budget saved = budgetService.save(newBudget);
    return budgetMapper.convertBudgetToBudgetCreateResponseDto(saved);
  }

  @GetMapping("/api/v1/budgets")
  public ListDto<BudgetCommonResponseDto> getSample(@RequestParam(name = "offset", required = false) Integer offset,
                                                    @RequestParam(name = "diapason", required = false) Integer diapason,
                                                    OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    List<Budget> budgets = budgetService.getSample(userId, offset, diapason);
    return budgetMapper.convertBudgetListToBudgetCommonResponseDtoList(budgets, offset, diapason);
  }

  @PutMapping("/api/v1/budgets/{budget-id}")
  public BudgetCommonResponseDto update(@PathVariable("budget-id") Long budgetId,
                                        @RequestBody BudgetCommonRequestDto budgetCommonRequestDto,
                                        OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(budgetId, userId);
    Budget budget = budgetMapper.convertBudgetCommonRequestDtoToBudget(userId, budgetCommonRequestDto);
    Budget updated = budgetService.update(compositeId, budget);
    return budgetMapper.convertBudgetToBudgetCommonResponseDto(updated);
  }

  @DeleteMapping("/api/v1/budgets/{budget-id}")
  public void delete(@PathVariable("budget-id") Long budgetId,
                     OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(budgetId, userId);
    budgetService.delete(compositeId);
  }

  @PostMapping("/api/v1/budgets/{budget-id}/incomes/{income-id}")
  public BudgetCommonResponseDto addIncome(@PathVariable("budget-id") Long budgetId,
                                           @PathVariable("income-id") Long incomeId,
                                           OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(budgetId, userId);
    Budget updatedBudget = budgetService.addIncome(compositeId, incomeId);
    return budgetMapper.convertBudgetToBudgetCommonResponseDto(updatedBudget);
  }

  @PostMapping("/api/v1/budgets/{budget-id}/expenses/{expense-id}")
  public BudgetCommonResponseDto addExpense(@PathVariable("budget-id") Long budgetId,
                                            @PathVariable("expense-id") Long expenseId,
                                            OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(budgetId, userId);
    Budget updatedBudget = budgetService.addExpense(compositeId, expenseId);
    return budgetMapper.convertBudgetToBudgetCommonResponseDto(updatedBudget);
  }

  @PostMapping("/api/v1/budgets/{budget-id}/income-plans/{income-plan-id}")
  public BudgetCommonResponseDto addIncomePlan(@PathVariable("budget-id") Long budgetId,
                                               @PathVariable("income-plan-id") Long incomePlanId,
                                               OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(budgetId, userId);
    Budget updatedBudget = budgetService.addIncomePlan(compositeId, incomePlanId);
    return budgetMapper.convertBudgetToBudgetCommonResponseDto(updatedBudget);
  }

  @PostMapping("/api/v1/budgets/{budget-id}/expense-plans/{expense-plan-id}")
  public BudgetCommonResponseDto addExpensePlan(@PathVariable("budget-id") Long budgetId,
                                                @PathVariable("expense-plan-id") Long expensePlanId,
                                                OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(budgetId, userId);
    Budget updatedBudget = budgetService.addExpensePlan(compositeId, expensePlanId);
    return budgetMapper.convertBudgetToBudgetCommonResponseDto(updatedBudget);
  }
}