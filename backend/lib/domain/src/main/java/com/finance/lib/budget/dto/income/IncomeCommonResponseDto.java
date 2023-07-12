package com.finance.lib.budget.dto.income;

import com.finance.lib.budget.dto.CompositeIdDto;
import com.finance.lib.budget.dto.OperationCommonResponseDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
public class IncomeCommonResponseDto extends OperationCommonResponseDto {
  private String source;
  private IncomeCategoryDto incomeCategory;

  public IncomeCommonResponseDto(String type,
                                 CompositeIdDto compositeId,
                                 String name,
                                 String description,
                                 OffsetDateTime dateTime,
                                 AmountDto amount,
                                 String source,
                                 IncomeCategoryDto incomeCategory) {
    super(type, compositeId, name, description, dateTime, amount);
    this.source = source;
    this.incomeCategory = incomeCategory;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    IncomeCommonResponseDto that = (IncomeCommonResponseDto) o;
    return Objects.equals(source, that.source) && incomeCategory == that.incomeCategory;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), source, incomeCategory);
  }
}
