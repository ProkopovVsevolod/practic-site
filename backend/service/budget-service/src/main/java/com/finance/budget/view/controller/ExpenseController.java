package com.finance.budget.view.controller;

import com.finance.budget.domain.CompositeId;
import com.finance.budget.domain.operation.expense.Expense;
import com.finance.budget.service.contract.ExpenseService;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.expense.ExpenseCommonRequestDto;
import com.finance.budget.view.dto.expense.ExpenseCommonResponseDto;
import com.finance.budget.view.mapper.ExpenseMapper;
import com.finance.jwt.domain.OpenAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
public class ExpenseController {
  private final ExpenseMapper expenseMapper;
  private final ExpenseService expenseService;

  @Autowired
  public ExpenseController(ExpenseMapper expenseMapper,
                           ExpenseService expenseService) {
    this.expenseMapper = expenseMapper;
    this.expenseService = expenseService;
  }

  @PostMapping("/api/v1/expenses")
  @ResponseStatus(HttpStatus.CREATED)
  public ExpenseCommonResponseDto create(@RequestBody ExpenseCommonRequestDto expenseCommonRequestDto,
                                         OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    Expense expense = expenseMapper.convertExpenseCommonRequestDtoToExpense(userId, expenseCommonRequestDto);
    Expense created = expenseService.save(expense);
    return expenseMapper.convertExpenseToExpenseCommonResponseDto(created);
  }

  @GetMapping("/api/v1/expenses")
  public ListDto<ExpenseCommonResponseDto> getSample(@RequestParam(name = "offset", required = false) Integer offset,
                                                     @RequestParam(name = "diapason", required = false) Integer diapason,
                                                     OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    List<Expense> expenseList = expenseService.getSample(userId, offset, diapason);
    return expenseMapper.convertExpenseListToExpenseCommonResponseDtoList(expenseList, offset, diapason);
  }

  @PutMapping("/api/v1/expenses/{expense-id}")
  public ExpenseCommonResponseDto update(@PathVariable("expense-id") Long expenseId,
                                         @RequestBody ExpenseCommonRequestDto expenseCommonRequestDto,
                                         OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(expenseId, userId);
    Expense newExpense = expenseMapper.convertExpenseCommonRequestDtoToExpense(userId, expenseCommonRequestDto);
    Expense updatedExpense = expenseService.update(compositeId, newExpense);
    return expenseMapper.convertExpenseToExpenseCommonResponseDto(updatedExpense);
  }

  @DeleteMapping("/api/v1/expenses/{expense-id}")
  public void delete(@PathVariable("expense-id") Long expenseId,
                     OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(expenseId, userId);
    expenseService.delete(compositeId);
  }
}
