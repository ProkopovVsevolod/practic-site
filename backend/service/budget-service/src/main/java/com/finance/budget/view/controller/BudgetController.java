package com.finance.budget.view.controller;

import com.finance.budget.domain.Budget;
import com.finance.budget.service.contract.BudgetService;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.budget.BudgetCommonResponseDto;
import com.finance.budget.view.dto.budget.BudgetCommonRequestDto;
import com.finance.budget.view.dto.budget.BudgetCreateResponseDto;
import com.finance.budget.view.mapper.BudgetMapper;
import com.finance.jwt.domain.OpenJwtToken;
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

  @PostMapping("/api/v1/users/budgets")
  @ResponseStatus(HttpStatus.CREATED)
  public BudgetCreateResponseDto create(@RequestBody BudgetCommonRequestDto createDto,
                                        OpenJwtToken openJwtToken) {
    Long userId = openJwtToken.getUserId();
    Budget newBudget = budgetMapper.convertBudgetCommonRequestDtoToBudget(userId, createDto);
    Budget saved = budgetService.save(newBudget);
    return budgetMapper.convertBudgetToBudgetCreateResponseDto(saved);
  }

  @GetMapping("/api/v1/users/budgets")
  public ListDto<BudgetCommonResponseDto> getSample(@RequestParam(name = "offset", required = false) Integer offset,
                                                    @RequestParam(name = "diapason", required = false) Integer diapason,
                                                    OpenJwtToken openJwtToken) {
    Long userId = openJwtToken.getUserId();
    List<Budget> budgets = budgetService.getSample(userId, offset, diapason);
    return budgetMapper.convertBudgetListToBudgetCommonResponseDtoList(budgets, offset, diapason);
  }

  @PutMapping("/api/v1/users/budgets/{budget-id}")
  public BudgetCommonResponseDto update(@PathVariable("budget-id") Long budgetId,
                                        @RequestBody BudgetCommonRequestDto budgetCommonRequestDto,
                                        OpenJwtToken openJwtToken) {
    Long userId = openJwtToken.getUserId();
    Budget budget = budgetMapper.convertBudgetCommonRequestDtoToBudget(userId, budgetCommonRequestDto);
    Budget updated = budgetService.update(budgetId, budget);
    return budgetMapper.convertBudgetToBudgetCommonResponseDto(updated);
  }

  @DeleteMapping("/api/v1/users/budgets/{budget-id}")
  public void delete(@PathVariable("budget-id") Long budgetId,
                     OpenJwtToken openJwtToken) {
    Long userId = openJwtToken.getUserId();
    budgetService.delete(userId, budgetId);
  }
}