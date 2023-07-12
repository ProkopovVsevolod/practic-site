package com.finance.budget.view.controller;

import com.finance.budget.service.contract.BudgetService;
import com.finance.jwt.domain.OpenAccessToken;
import com.finance.lib.budget.domain.entity.Budget;
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
import com.finance.lib.budget.dto.amount.CurrencyDto;
import com.finance.lib.budget.dto.budget.BudgetCommonRequestDto;
import com.finance.lib.budget.dto.budget.BudgetCommonResponseDto;
import com.finance.lib.budget.dto.budget.BudgetCreateResponseDto;
import com.finance.lib.budget.dto.operation.expense.ExpenseCategoryDto;
import com.finance.lib.budget.dto.operation.expense.ExpenseCommonResponseDto;
import com.finance.lib.budget.dto.operation.expense.plan.ExpensePlanCommonResponseDto;
import com.finance.lib.budget.dto.operation.income.IncomeCategoryDto;
import com.finance.lib.budget.dto.operation.income.IncomeCommonResponseDto;
import com.finance.lib.budget.dto.operation.income.plan.IncomePlanCommonResponseDto;
import com.finance.lib.budget.mapper.*;
import com.finance.lib.budget.mapper.enums.ExpenseEnumMapper;
import com.finance.lib.budget.mapper.enums.IncomeEnumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
public class BudgetController {
  private final BudgetMapper budgetMapper;
  private final BudgetService budgetService;
  private final AmountMapper amountMapper;
  private final PeriodMapper periodMapper;
  private final IncomeMapper incomeMapper;
  private final ExpenseMapper expenseMapper;
  private final IncomeEnumMapper incomeEnumMapper;
  private final ExpenseEnumMapper expenseEnumMapper;
  private final IncomePlanMapper incomePlanMapper;
  private final ExpensePlanMapper expensePlanMapper;


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

  @GetMapping("/api/v1/budgets/{budget-id}/currency")
  public CurrencyDto getBudgetCurrency(@PathVariable("budget-id") long budgetId,
                                       OpenAccessToken openAccessToken) {
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
    return amountMapper.convert(budgetService.getBudgetCurrency(compositeId));
  }

  @GetMapping("/api/v1/budgets/{budget-id}/incomes/{year}-{month}:{duration}")
  public ListDto<IncomeCommonResponseDto> getBudgetIncomesByPeriod(@PathVariable("budget-id") long budgetId,
                                                                   @PathVariable("year") int year,
                                                                   @PathVariable("month") int month,
                                                                   @PathVariable("duration") String duration,
                                                                   OpenAccessToken openAccessToken) {
    PeriodDto periodDto = new PeriodDto(year, month, DurationDto.createByName(duration));
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
    Period period = periodMapper.convert(periodDto);

    List<Income> incomes = budgetService.getBudgetIncomesByPeriod(compositeId, period);
    return incomeMapper.convertIncomeListToIncomeCommonResponseDtoList(incomes);
  }

  @GetMapping("/api/v1/budgets/{budget-id}/expenses/{year}-{month}:{duration}")
  public ListDto<ExpenseCommonResponseDto> getBudgetExpensesByPeriod(@PathVariable("budget-id") long budgetId,
                                                                     @PathVariable("year") int year,
                                                                     @PathVariable("month") int month,
                                                                     @PathVariable("duration") String duration,
                                                                     OpenAccessToken openAccessToken) {
    PeriodDto periodDto = new PeriodDto(year, month, DurationDto.createByName(duration));
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
    Period period = periodMapper.convert(periodDto);

    List<Expense> expenses = budgetService.getBudgetExpensesByPeriod(compositeId, period);
    return expenseMapper.convertExpenseListToExpenseCommonResponseDtoList(expenses);
  }

  @GetMapping("/api/v1/budgets/{budget-id}/balance")
  public AmountDto getBalance(@PathVariable("budget-id") long budgetId,
                              OpenAccessToken openAccessToken) {
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());
    Amount balance = budgetService.getBalance(compositeId);
    return amountMapper.convert(balance);
  }

  @GetMapping("/api/v1/budgets/{budget-id}/income-plans/{year}-{month}/{category}")
  public IncomePlanCommonResponseDto getIncomePlan(@PathVariable("budget-id") long budgetId,
                                                   @PathVariable("year") int year,
                                                   @PathVariable("month") int month,
                                                   @PathVariable("category") String categoryName,
                                                   OpenAccessToken openAccessToken) {
    IncomeCategoryDto categoryDto = IncomeCategoryDto.createByName(categoryName);
    IncomeCategory category = incomeEnumMapper.convert(categoryDto);
    PeriodDto periodDto = new PeriodDto(year, month);
    Period period = periodMapper.convert(periodDto);
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());

    IncomePlan plan = budgetService.getBudgetIncomePlans(compositeId, period, category);
    return incomePlanMapper.convertIncomePlanToIncomePlanCommonResponseDto(plan);
  }

  @GetMapping("/api/v1/budgets/{budget-id}/expense-plans/{year}-{month}/{category}")
  public ExpensePlanCommonResponseDto getExpensePlan(@PathVariable("budget-id") long budgetId,
                                                     @PathVariable("year") int year,
                                                     @PathVariable("month") int month,
                                                     @PathVariable("category") String categoryName,
                                                     OpenAccessToken openAccessToken) {
    ExpenseCategoryDto categoryDto = ExpenseCategoryDto.createByName(categoryName);
    ExpenseCategory category = expenseEnumMapper.convert(categoryDto);
    PeriodDto periodDto = new PeriodDto(year, month);
    Period period = periodMapper.convert(periodDto);
    CompositeId compositeId = new CompositeId(budgetId, openAccessToken.getUserId());

    ExpensePlan plan = budgetService.getBudgetExpensePlans(compositeId, period, category);
    return expensePlanMapper.convertExpensePlanToExpensePlanCommonResponseDto(plan);
  }
}