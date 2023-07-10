package com.finance.budget.view.dto.expense;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum ExpenseCategoryDto {
  FOOD,
  HOUSING,
  TRANSPORTATION,
  HEALTH_AND_MEDICINE,
  ENTERTAINMENT_AND_LEISURE,
  CLOTHES_AND_SHOES,
  EDUCATION,
  COMMUNICATIONS_AND_THE_INTERNET,
  FINANCIAL_EXPENSES,
  CHARITY,
  FAMILY_AND_CHILDREN,
  PETS,
  BEAUTY_AND_CARE,
  APPLIANCES_AND_ELECTRONICS,
  LONG_TERM_INVESTMENTS,
  INSURANCE,
  TAXES,
  REPAIRS_AND_MAINTENANCE,
  GIFTS_AND_HOLIDAYS,
  WORK_EXPENSES,
  HOBBIES,
  SPORTS_AND_FITNESS,
  RESTAURANTS_AND_CAFES,
  UNEXPECTED_EXPENSES,
  ANOTHER;

  private static final Map<String, ExpenseCategoryDto> jsonNamesMap = new HashMap<>() {
    {
      put("food", FOOD);
      put("housing", HOUSING);
      put("transportation", TRANSPORTATION);
      put("healthAndMedicine", HEALTH_AND_MEDICINE);
      put("entertainmentAndLeisure", ENTERTAINMENT_AND_LEISURE);
      put("clothesAndShoes", CLOTHES_AND_SHOES);
      put("education", EDUCATION);
      put("communicationsAndTheInternet", COMMUNICATIONS_AND_THE_INTERNET);
      put("financialExpenses", FINANCIAL_EXPENSES);
      put("charity", CHARITY);
      put("familyAndChildren", FAMILY_AND_CHILDREN);
      put("pets", PETS);
      put("beautyAndCare", BEAUTY_AND_CARE);
      put("appliancesAndElectronics", APPLIANCES_AND_ELECTRONICS);
      put("logTermInvestments", LONG_TERM_INVESTMENTS);
      put("insurance", INSURANCE);
      put("taxes", TAXES);
      put("repairsAndMaintenance", REPAIRS_AND_MAINTENANCE);
      put("giftsAndHolidays", GIFTS_AND_HOLIDAYS);
      put("workExpenses", WORK_EXPENSES);
      put("hobbies", HOBBIES);
      put("sportsAndFitness", SPORTS_AND_FITNESS);
      put("restaurantsAndCafes", RESTAURANTS_AND_CAFES);
      put("unexpectedExpenses", UNEXPECTED_EXPENSES);
      put("another", ANOTHER);
    }
  };


  public static ExpenseCategoryDto createByName(String name) {
    ExpenseCategoryDto expenseCategoryDto = jsonNamesMap.get(name);
    if (expenseCategoryDto == null) {
      throw new IllegalArgumentException("PaymentMethodDto by name " + name + "not supported");
    }
    return expenseCategoryDto;
  }

  @JsonValue
  public String toName() {
    for (Map.Entry<String, ExpenseCategoryDto> entry : jsonNamesMap.entrySet()) {
      if (entry.getValue() == this) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("PaymentMethodDto type: " + this + " is not supported");
  }
}
