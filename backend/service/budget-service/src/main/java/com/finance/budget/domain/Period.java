package com.finance.budget.domain;

import lombok.Getter;

@Getter
public enum Period {
  MONTH("month"),
  QUARTER("quarter"),
  HALF_YEAR("halfYear"),
  YEAR("year"),
  TWO_YEAR("twoYear");

  private final String name;

  Period(String name) {
    this.name = name;
  }

  public static Period byName(String name) {
    for (Period period : Period.values()) {
      if (period.name.equals(name)) {
        return period;
      }
    }
    throw new IllegalArgumentException("Period with name: " + name + " not found");
  }


}
