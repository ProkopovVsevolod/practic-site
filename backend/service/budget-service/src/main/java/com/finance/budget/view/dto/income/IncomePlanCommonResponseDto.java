package com.finance.budget.view.dto.income;

import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.PeriodDto;
import com.finance.budget.view.dto.amount.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomePlanCommonResponseDto {
  private Long id;
  private AmountDto limit;
  private PeriodDto period;
  private ListDto<IncomeCategoryDto> categories;
}

