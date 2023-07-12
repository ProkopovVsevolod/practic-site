package com.finance.analysis.view.dto;

import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.OperationCommonResponseDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanActualCompareDto {
  private AmountDto diff;
  private PlanDto planDto;
  private ListDto<OperationCommonResponseDto> operationList;
}
