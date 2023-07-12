package com.finance.budget.view.controller;

import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.operation.income.IncomePlan;
import com.finance.budget.service.contract.IncomePlanService;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.income.plan.IncomePlanCommonRequestDto;
import com.finance.lib.budget.dto.income.plan.IncomePlanCommonResponseDto;
import com.finance.lib.budget.mapper.IncomePlanMapper;
import com.finance.jwt.domain.OpenAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseStatus(HttpStatus.OK)
public class IncomePlanController {
  private final IncomePlanMapper incomePlanMapper;
  private final IncomePlanService incomePlanService;

  @Autowired
  public IncomePlanController(IncomePlanMapper incomePlanMapper,
                              IncomePlanService incomePlanService) {
    this.incomePlanMapper = incomePlanMapper;
    this.incomePlanService = incomePlanService;
  }

  @PostMapping("/api/v1/income-plans")
  @ResponseStatus(HttpStatus.CREATED)
  public IncomePlanCommonResponseDto create(@RequestBody IncomePlanCommonRequestDto incomePlanCreateRequestDto,
                                            OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    IncomePlan incomePlan = incomePlanMapper.convertIncomeCommonRequestDtoToIncome(userId, incomePlanCreateRequestDto);
    IncomePlan createdPlan = incomePlanService.save(incomePlan);
    return incomePlanMapper.convertIncomeToIncomePlanCommonResponseDto(createdPlan);
  }

  @GetMapping("/api/v1/income-plans")
  public ListDto<IncomePlanCommonResponseDto> getSample(@RequestParam(name = "offset", required = false) Integer offset,
                                                        @RequestParam(name = "diapason", required = false) Integer diapason,
                                                        OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    List<IncomePlan> incomeList = incomePlanService.getSample(userId, offset, diapason);
    return incomePlanMapper.convertIncomePlanListToIncomePlanCommonResponseDtoList(incomeList, offset, diapason);
  }

  @PutMapping("/api/v1/income-plans/{income-plan-id}")
  public IncomePlanCommonResponseDto update(@PathVariable("income-plan-id") Long incomePlanId,
                                            @RequestBody IncomePlanCommonRequestDto incomeUpdateRequestDto,
                                            OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(incomePlanId, userId);
    IncomePlan newIncomePlan = incomePlanMapper.convertIncomeCommonRequestDtoToIncome(userId, incomeUpdateRequestDto);
    IncomePlan updatedIncomePlan = incomePlanService.update(compositeId, newIncomePlan);
    return incomePlanMapper.convertIncomePlanToIncomePlanCommonResponseDto(updatedIncomePlan);
  }

  @DeleteMapping("/api/v1/income-plans/{income-plan-id}")
  public void delete(@PathVariable("income-plan-id") Long incomePlanId,
                     OpenAccessToken openAccessToken) {
    Long userId = openAccessToken.getUserId();
    CompositeId compositeId = new CompositeId(incomePlanId, userId);
    incomePlanService.delete(compositeId);
  }
}
