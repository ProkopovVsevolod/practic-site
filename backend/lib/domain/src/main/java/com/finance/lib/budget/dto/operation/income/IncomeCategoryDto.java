package com.finance.lib.budget.dto.operation.income;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum IncomeCategoryDto {
  SALARY,
  PASSIVE,
  ADDITIONAL,
  PENSION,
  SOCIAL_BENEFIT,
  SCHOLARSHIP,
  GRANT,
  ALIMONY,
  PRESENT,
  INHERITANCE,
  ANOTHER;

  private static final Map<String, IncomeCategoryDto> jsonNamesMap = new HashMap<>() {
    {
      put("salary", SALARY);
      put("passive", PASSIVE);
      put("additional", ADDITIONAL);
      put("pension", PENSION);
      put("socialBenefit", SOCIAL_BENEFIT);
      put("scholarship", SCHOLARSHIP);
      put("grant", GRANT);
      put("alimony", ALIMONY);
      put("present", PRESENT);
      put("inheritance", INHERITANCE);
      put("another", ANOTHER);
    }
  };


  public static IncomeCategoryDto createByName(String name) {
    IncomeCategoryDto incomeCategoryDto = jsonNamesMap.get(name);
    if (incomeCategoryDto == null) {
      throw new IllegalArgumentException("IncomeCategoryDto by name " + name + "not supported");
    }
    return incomeCategoryDto;
  }

  @JsonValue
  public String toName() {
    for (Map.Entry<String, IncomeCategoryDto> entry : jsonNamesMap.entrySet()) {
      if (entry.getValue() == this) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("IncomeCategoryDto type: " + this + " is not supported");
  }
}
