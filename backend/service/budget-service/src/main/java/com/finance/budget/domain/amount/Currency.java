package com.finance.budget.domain.amount;

import lombok.Getter;

@Getter
public enum Currency {
  RUB(643, "rub",'₽'), USD(840, "usd",'$');

  private final Integer code;
  private final String name;
  private final Character symbol;

  Currency(Integer code, String name, Character symbol) {
    this.code = code;
    this.name = name;
    this.symbol = symbol;
  }

  public static Currency byName(String name) {
    for (Currency currency : Currency.values()) {
      if (currency.name.equals(name)) {
        return currency;
      }
    }
    throw new IllegalArgumentException("Currency with name: " + name + " not found");
  }
}
