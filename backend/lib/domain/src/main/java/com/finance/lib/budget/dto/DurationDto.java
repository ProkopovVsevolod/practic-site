package com.finance.lib.budget.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;

public enum DurationDto {
  MONTH,
  QUARTER,
  HALF_YEAR,
  YEAR,
  TWO_YEAR;

  private static final Map<String, DurationDto> jsonNamesMap = Map.of(
    "month", MONTH,
    "quarter", QUARTER,
    "halfYear", HALF_YEAR,
    "year", YEAR,
    "twoYear", TWO_YEAR
  );

  @JsonCreator
  public static DurationDto createByName(String duration) {
    DurationDto durationDto = jsonNamesMap.get(duration);
    if (durationDto == null) {
      throw new IllegalArgumentException("DurationDto by name " + duration + "not supported");
    }
    return durationDto;
  }

  @JsonValue
  public String toName() {
    for (Map.Entry<String, DurationDto> entry : jsonNamesMap.entrySet()) {
      if (entry.getValue() == this) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("DurationDto type: " + this + " is not supported");
  }
}
