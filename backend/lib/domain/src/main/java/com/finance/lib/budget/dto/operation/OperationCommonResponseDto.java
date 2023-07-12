package com.finance.lib.budget.dto.operation;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.finance.lib.budget.dto.CompositeIdDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationCommonResponseDto {
  protected String type;
  @JsonUnwrapped
  protected CompositeIdDto compositeId;
  protected String name;
  protected String description;
  protected OffsetDateTime dateTime;
  protected AmountDto amount;
}
