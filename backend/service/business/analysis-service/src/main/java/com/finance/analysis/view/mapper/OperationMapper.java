package com.finance.analysis.view.mapper;

import com.finance.analysis.view.dto.PlanActualCompareDto;
import com.finance.analysis.view.dto.PlanDto;
import com.finance.lib.budget.domain.entity.Operation;
import com.finance.lib.budget.domain.entity.Plan;
import com.finance.lib.budget.domain.entity.amount.Amount;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.operation.OperationAnalyseResponseDto;
import com.finance.lib.budget.mapper.AmountMapper;
import com.finance.lib.budget.mapper.PeriodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OperationMapper {
  private final PeriodMapper periodMapper;
  private final AmountMapper amountMapper;

  public OperationAnalyseResponseDto convert(Operation operation) {
    return new OperationAnalyseResponseDto(
      operation.getType(),
      operation.getName(),
      operation.getDescription(),
      operation.getDateTime(),
      amountMapper.convert(operation.getAmount())
    );
  }

  public ListDto<OperationAnalyseResponseDto> convertList(List<? extends Operation> operationList) {
    List<OperationAnalyseResponseDto> listDto = operationList.stream()
      .map(this::convert)
      .toList();
    return new ListDto<>(listDto);
  }

  public PlanActualCompareDto convert(Amount diff, Plan plan, List<? extends Operation> operationList) {
    return new PlanActualCompareDto(
      amountMapper.convert(diff),
      new PlanDto(amountMapper.convert(plan.getLimit()), periodMapper.convert(plan.getPeriod())),
      convertList(operationList)
    );
  }
}
