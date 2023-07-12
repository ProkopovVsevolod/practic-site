package com.finance.budget.view.controller;

import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.operation.expense.ExpensePlan;
import com.finance.budget.service.contract.ExpensePlanService;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.operation.expense.plan.ExpensePlanCommonRequestDto;
import com.finance.lib.budget.dto.operation.expense.plan.ExpensePlanCommonResponseDto;
import com.finance.lib.budget.mapper.ExpensePlanMapper;
import com.finance.jwt.domain.OpenAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
public class ExpensePlanController {
  private final ExpensePlanMapper expensePlanMapper;
  private final ExpensePlanService expensePlanService;

  @Autowired
  public ExpensePlanController(ExpensePlanMapper expensePlanMapper,
                               ExpensePlanService expensePlanService) {
    this.expensePlanMapper = expensePlanMapper;
    this.expensePlanService = expensePlanService;
  }

  @PostMapping("/api/v1/expense-plans")
  @ResponseStatus(HttpStatus.CREATED)
  public ExpensePlanCommonResponseDto create(@RequestBody ExpensePlanCommonRequestDto expensePlanCreateRequestDto,
                                             OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    ExpensePlan expensePlan = expensePlanMapper.convertExpensePlanCommonRequestDtoToExpensePlan(userId, expensePlanCreateRequestDto);
    ExpensePlan createdPlan = expensePlanService.save(expensePlan);
    return expensePlanMapper.convertExpensePlanToExpensePlanCommonResponseDto(createdPlan);
  }

  @GetMapping("/api/v1/expense-plans")
  public ListDto<ExpensePlanCommonResponseDto> getSample(@RequestParam(name = "offset", required = false) Integer offset,
                                                         @RequestParam(name = "diapason", required = false) Integer diapason,
                                                         OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    List<ExpensePlan> expensePlanList = expensePlanService.getSample(userId, offset, diapason);
    return expensePlanMapper.convertExpensePlanListToExpensePlanCommonResponseDtoList(expensePlanList, offset, diapason);
  }

  @PutMapping("/api/v1/expense-plans/{expense-plan-id}")
  public ExpensePlanCommonResponseDto update(@PathVariable("expense-plan-id") Long expensePlanId,
                                             @RequestBody ExpensePlanCommonRequestDto expensePlanCommonRequestDto,
                                             OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(expensePlanId, userId);
    ExpensePlan newExpensePlan = expensePlanMapper.convertExpensePlanCommonRequestDtoToExpensePlan(userId, expensePlanCommonRequestDto);
    ExpensePlan updatedExpensePlan = expensePlanService.update(compositeId, newExpensePlan);
    return expensePlanMapper.convertExpensePlanToExpensePlanCommonResponseDto(updatedExpensePlan);
  }

  @DeleteMapping("/api/v1/expense-plans/{expense-plan-id}")
  public void delete(@PathVariable("expense-plan-id") Long expensePlanId,
                     OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(expensePlanId, userId);
    expensePlanService.delete(compositeId);
  }
}
