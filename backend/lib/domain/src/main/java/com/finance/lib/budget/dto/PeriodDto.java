package com.finance.lib.budget.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PeriodDto {
  private int year;
  private int month;
  private DurationDto duration;

  @JsonCreator
  public PeriodDto(@JsonProperty("year") int year,
                   @JsonProperty("month") int month,
                   @JsonProperty("duration") DurationDto duration) {
    this.year = year;
    this.month = month;
    this.duration = duration == null ? DurationDto.MONTH : duration;
  }

  public PeriodDto(int year, int month) {
    this(year, month, null);
  }
}
