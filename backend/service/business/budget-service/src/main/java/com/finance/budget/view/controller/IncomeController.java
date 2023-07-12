package com.finance.budget.view.controller;

import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.operation.income.Income;
import com.finance.lib.budget.domain.entity.operation.income.IncomeCategory;
import com.finance.budget.service.contract.IncomeService;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.income.IncomeCommonResponseDto;
import com.finance.lib.budget.dto.income.IncomeCommonRequestDto;
import com.finance.lib.budget.mapper.IncomeMapper;
import com.finance.jwt.domain.OpenAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
public class IncomeController {
  private final IncomeMapper incomeMapper;
  private final IncomeService incomeService;

  @Autowired
  public IncomeController(IncomeMapper incomeMapper,
                          IncomeService incomeService) {
    this.incomeMapper = incomeMapper;
    this.incomeService = incomeService;
  }

  @PostMapping("/api/v1/incomes")
  @ResponseStatus(HttpStatus.CREATED)
  public IncomeCommonResponseDto create(@RequestBody IncomeCommonRequestDto incomeCommonRequestDto,
                                        OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    Income income = incomeMapper.convertIncomeCreateRequestDtoToIncome(userId, incomeCommonRequestDto);
    Income created = incomeService.save(income);
    return incomeMapper.convertIncomeToIncomeCommonResponseDto(created);
  }

  @GetMapping("/api/v1/incomes")
  public ListDto<IncomeCommonResponseDto> getSample(@RequestParam(name = "offset", required = false) Integer offset,
                                                    @RequestParam(name = "diapason", required = false) Integer diapason,
                                                    OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    List<Income> incomeList = incomeService.getSample(userId, offset, diapason);
    return incomeMapper.convertIncomeListToIncomeCommonResponseDtoList(incomeList, offset, diapason);
  }

  @PutMapping("/api/v1/incomes/{income-id}")
  public IncomeCommonResponseDto update(@PathVariable("income-id") Long incomeId,
                                        @RequestBody IncomeCommonRequestDto incomeCommonRequestDto,
                                        OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(incomeId, userId);
    Income newIncome = incomeMapper.convertIncomeUpdateRequestDtoToIncome(userId, incomeCommonRequestDto);
    Income updatedIncome = incomeService.update(compositeId, newIncome);
    return incomeMapper.convertIncomeToIncomeCommonResponseDto(updatedIncome);
  }

  @DeleteMapping("/api/v1/incomes/{income-id}")
  public void delete(@PathVariable("income-id") Long incomeId,
                     OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(incomeId, userId);
    incomeService.delete(compositeId);
  }

  @GetMapping("/api/v1/incomes/categories")
  public IncomeCategory[] getIncomeCategories() {
    return IncomeCategory.values();
  }
}
