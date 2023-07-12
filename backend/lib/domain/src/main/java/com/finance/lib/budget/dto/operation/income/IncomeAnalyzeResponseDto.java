package com.finance.lib.budget.dto.operation.income;

import com.finance.lib.budget.dto.operation.OperationAnalyseResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class IncomeAnalyzeResponseDto extends OperationAnalyseResponseDto {
  private String source;
  private IncomeCategoryDto incomeCategory;
}
