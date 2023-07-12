package com.finance.lib.budget.dto.operation;

import com.finance.lib.budget.dto.amount.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationAnalyseResponseDto {
  protected String type;
  protected String name;
  protected String description;
  protected OffsetDateTime dateTime;
  protected AmountDto amount;
}
