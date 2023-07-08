package com.finance.budget.view.dto.income;

import com.finance.budget.view.dto.CompositeIdDto;
import com.finance.budget.view.dto.amount.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeCommonResponseDto {
  private CompositeIdDto compositeId;
  private String name;
  private String description;
  private String source;
  private OffsetDateTime dateTime;
  private IncomeCategoryDto incomeCategory;
  private AmountDto amount;
}
