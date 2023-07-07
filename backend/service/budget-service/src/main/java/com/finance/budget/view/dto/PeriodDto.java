package com.finance.budget.view.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;

public enum PeriodDto {
  MONTH,
  QUARTER,
  HALF_YEAR,
  YEAR,
  TWO_YEAR;

  private static final Map<String, PeriodDto> jsonNamesMap = Map.of(
    "month", MONTH,
    "quarter", QUARTER,
    "halfYear", HALF_YEAR,
    "year", YEAR,
    "twoYear", TWO_YEAR
  );

  @JsonCreator
  public static PeriodDto createByName(String name) {
    PeriodDto periodDto = jsonNamesMap.get(name);
    if (periodDto == null) {
      throw new IllegalArgumentException("PeriodDto by name " + name + "not supported");
    }
    return periodDto;
  }

  @JsonValue
  public String toName() {
    for (Map.Entry<String, PeriodDto> entry : jsonNamesMap.entrySet()) {
      if (entry.getValue() == this) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("PeriodDto type: " + this + " is not supported");
  }
}
