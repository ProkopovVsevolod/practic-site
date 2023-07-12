package com.finance.lib.budget.domain.entity.operation.expense;

import lombok.Getter;

@Getter
public enum ExpenseCategory {
  FOOD("food"),
  HOUSING("housing"),
  TRANSPORTATION("transportation"),
  HEALTH_AND_MEDICINE("healthAndMedicine"),
  ENTERTAINMENT_AND_LEISURE("entertainmentAndLeisure"),
  CLOTHES_AND_SHOES("clothesAndShoes"),
  EDUCATION("education"),
  COMMUNICATIONS_AND_THE_INTERNET("communicationsAndTheInternet"),
  FINANCIAL_EXPENSES("financialExpenses"),
  CHARITY("charity"),
  FAMILY_AND_CHILDREN("familyAndChildren"),
  PETS("pets"),
  BEAUTY_AND_CARE("beautyAndCare"),
  APPLIANCES_AND_ELECTRONICS("appliancesAndElectronics"),
  LONG_TERM_INVESTMENTS("logTermInvestments"),
  INSURANCE("insurance"),
  TAXES("taxes"),
  REPAIRS_AND_MAINTENANCE("repairsAndMaintenance"),
  GIFTS_AND_HOLIDAYS("giftsAndHolidays"),
  WORK_EXPENSES("workExpenses"),
  HOBBIES("hobbies"),
  SPORTS_AND_FITNESS("sportsAndFitness"),
  RESTAURANTS_AND_CAFES("restaurantsAndCafes"),
  UNEXPECTED_EXPENSES("unexpectedExpenses"),
  ANOTHER("another");

  private final String name;

  ExpenseCategory(String name) {
    this.name = name;
  }

  public static ExpenseCategory byName(String name) {
    for (ExpenseCategory expenseCategory : ExpenseCategory.values()) {
      if (expenseCategory.name.equals(name)) {
        return expenseCategory;
      }
    }
    throw new IllegalArgumentException("ExpenseCategory with name: " + name + " not found");
  }
}
