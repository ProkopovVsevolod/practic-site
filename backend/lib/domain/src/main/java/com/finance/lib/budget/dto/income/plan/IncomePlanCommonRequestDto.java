package com.finance.lib.budget.dto.income.plan;

import com.finance.lib.budget.dto.amount.AmountDto;
import com.finance.lib.budget.dto.income.IncomeCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomePlanCommonRequestDto {
  private AmountDto limit;
  private IncomeCategoryDto category;
}
