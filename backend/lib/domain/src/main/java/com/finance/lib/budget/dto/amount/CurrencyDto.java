package com.finance.lib.budget.dto.amount;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;

public enum CurrencyDto {
  RUB, USD;

  private static final Map<String, CurrencyDto> jsonNamesMap = Map.of(
    "rub", RUB,
    "usd", USD
  );

  @JsonCreator
  public static CurrencyDto createByName(String name) {
    CurrencyDto currencyDto = jsonNamesMap.get(name);
    if (currencyDto == null) {
      throw new IllegalArgumentException("CurrencyDto by name " + name + "not supported");
    }
    return currencyDto;
  }

  @JsonValue
  public String toName() {
    for (Map.Entry<String, CurrencyDto> entry : jsonNamesMap.entrySet()) {
      if (entry.getValue() == this) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("CurrencyDto type: " + this + " is not supported");
  }
}
