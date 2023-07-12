package com.finance.analysis.view.dto;

import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import com.finance.lib.budget.dto.operation.OperationAnalyseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanActualCompareDto {
  private AmountDto diff;
  private PlanDto plan;
  private AmountDto actual;
  private ListDto<OperationAnalyseResponseDto> operationList;

  public PlanActualCompareDto(AmountDto diff, PlanDto plan, ListDto<OperationAnalyseResponseDto> operationList) {
    this.diff = diff;
    this.plan = plan;
    this.actual = new AmountDto(plan.getLimit().getValue().subtract(diff.getValue()), diff.getCurrency());
    this.operationList = operationList;
  }
}
