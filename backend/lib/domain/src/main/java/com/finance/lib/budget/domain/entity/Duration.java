package com.finance.lib.budget.domain.entity;

import lombok.Getter;

@Getter
public enum Duration {
  MONTH("month", 1),
  QUARTER("quarter", 3),
  HALF_YEAR("halfYear", 6),
  YEAR("year", 12),
  TWO_YEAR("twoYear", 24);

  private final String name;
  private final long months;

  Duration(String name, long months) {
    this.name = name;
    this.months = months;
  }

  public static Duration byName(String name) {
    for (Duration duration : Duration.values()) {
      if (duration.name.equals(name)) {
        return duration;
      }
    }
    throw new IllegalArgumentException("Duration with name: " + name + " not found");
  }
}
