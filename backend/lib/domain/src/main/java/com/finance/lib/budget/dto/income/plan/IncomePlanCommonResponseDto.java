package com.finance.lib.budget.dto.income.plan;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.finance.lib.budget.dto.CompositeIdDto;
import com.finance.lib.budget.dto.PeriodDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import com.finance.lib.budget.dto.income.IncomeCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomePlanCommonResponseDto {
  @JsonUnwrapped
  private CompositeIdDto compositeId;
  private AmountDto limit;
  private PeriodDto period;
  private IncomeCategoryDto category;
}

