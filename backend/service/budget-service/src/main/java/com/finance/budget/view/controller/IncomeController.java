package com.finance.budget.view.controller;

import com.finance.budget.domain.operation.income.Income;
import com.finance.budget.service.contract.IncomeService;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.income.IncomeCommonResponseDto;
import com.finance.budget.view.dto.income.IncomeCreateRequestDto;
import com.finance.budget.view.dto.income.IncomeUpdateRequestDto;
import com.finance.budget.view.mapper.IncomeMapper;
import com.finance.jwt.domain.OpenJwtToken;
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

  @PostMapping("/api/v1/users/incomes")
  @ResponseStatus(HttpStatus.CREATED)
  public IncomeCommonResponseDto create(@RequestBody IncomeCreateRequestDto incomeCreateRequestDto,
                                        OpenJwtToken openJwtToken) {
    Long userId = openJwtToken.getUserId();
    Income income = incomeMapper.convertIncomeCreateRequestDtoToIncome(userId, incomeCreateRequestDto);
    Income created = incomeService.save(income);
    return incomeMapper.convertIncomeToIncomeCommonResponseDto(created);
  }

  @GetMapping("/api/v1/users/incomes")
  public ListDto<IncomeCommonResponseDto> getSample(@RequestParam(name = "offset", required = false) Integer offset,
                                                    @RequestParam(name = "diapason", required = false) Integer diapason,
                                                    OpenJwtToken openJwtToken) {
    Long userId = openJwtToken.getUserId();
    List<Income> incomeList = incomeService.getSample(userId, offset, diapason);
    return incomeMapper.convertIncomeListToIncomeCommonResponseDtoList(incomeList, offset, diapason);
  }

  @PutMapping("/api/v1/users/incomes/{income-id}")
  public IncomeCommonResponseDto update(@PathVariable("income-id") Long incomeId,
                                        @RequestBody IncomeUpdateRequestDto incomeUpdateRequestDto,
                                        OpenJwtToken openJwtToken) {
    Long userId = openJwtToken.getUserId();
    Income newIncome = incomeMapper.convertIncomeUpdateRequestDtoToIncome(userId, incomeUpdateRequestDto);
    Income updatedIncome = incomeService.update(incomeId, newIncome);
    return incomeMapper.convertIncomeToIncomeCommonResponseDto(updatedIncome);
  }

  @DeleteMapping("/api/v1/users/incomes/{income-id}")
  public void delete(@PathVariable("income-id") Long incomeId,
                     OpenJwtToken openJwtToken) {
    Long userId = openJwtToken.getUserId();
    incomeService.delete(userId, incomeId);
  }
}
