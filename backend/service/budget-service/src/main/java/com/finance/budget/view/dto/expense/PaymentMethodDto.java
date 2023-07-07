package com.finance.budget.view.dto.expense;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum PaymentMethodDto {
  CASH,
  BANK_CARD,
  BANK_TRANSFER,
  BANK_CHECK,
  ELECTRONIC_WALLET,
  ONLINE_BANK_TRANSFER,
  PAYMENT_SYSTEM,
  CRYPTOCURRENCIES,
  CHECK,
  BILL_OF_EXCHANGE,
  ANOTHER;

  private static final Map<String, PaymentMethodDto> jsonNamesMap = new HashMap<>() {
    {
      put("cash", CASH);
      put("bankCard", BANK_CARD);
      put("bankTransfer", BANK_TRANSFER);
      put("bankCheck", BANK_CHECK);
      put("electronicWallet", ELECTRONIC_WALLET);
      put("onlineBankTransfer", ONLINE_BANK_TRANSFER);
      put("paymentSystem", PAYMENT_SYSTEM);
      put("cryptocurrencies", CRYPTOCURRENCIES);
      put("check", CHECK);
      put("billOfExchange", BILL_OF_EXCHANGE);
      put("another", ANOTHER);
    }
  };


  public static PaymentMethodDto createByName(String name) {
    PaymentMethodDto paymentMethodDto = jsonNamesMap.get(name);
    if (paymentMethodDto == null) {
      throw new IllegalArgumentException("PaymentMethodDto by name " + name + "not supported");
    }
    return paymentMethodDto;
  }

  @JsonValue
  public String toName() {
    for (Map.Entry<String, PaymentMethodDto> entry : jsonNamesMap.entrySet()) {
      if (entry.getValue() == this) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("PaymentMethodDto type: " + this + " is not supported");
  }
}
