package com.finance.lib.budget.domain.entity.operation.income;

import lombok.Getter;

@Getter
public enum IncomeCategory {
  SALARY("salary"),
  PASSIVE("passive"),
  ADDITIONAL("additional"),
  PENSION("pension"),
  SOCIAL_BENEFIT("socialBenefit"),
  SCHOLARSHIP("scholarship"),
  GRANT("grant"),
  ALIMONY("alimony"),
  PRESENT("present"),
  INHERITANCE("inheritance"),
  ANOTHER("another");

  private final String name;

  IncomeCategory(String name) {
    this.name = name;
  }

  public static IncomeCategory byName(String name) {
    for (IncomeCategory incomeCategory : IncomeCategory.values()) {
      if (incomeCategory.name.equals(name)) {
        return incomeCategory;
      }
    }
    throw new IllegalArgumentException("IncomeCategory with name: " + name + " not found");
  }
}
