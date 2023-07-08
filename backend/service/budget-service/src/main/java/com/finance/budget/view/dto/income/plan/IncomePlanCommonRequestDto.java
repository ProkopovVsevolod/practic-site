package com.finance.budget.view.dto.income.plan;

import com.finance.budget.view.dto.PeriodDto;
import com.finance.budget.view.dto.amount.AmountDto;
import com.finance.budget.view.dto.income.IncomeCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomePlanCommonRequestDto {
  private AmountDto limit;
  private PeriodDto period;
  private List<IncomeCategoryDto> categories;
}
