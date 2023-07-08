package com.finance.budget.domain.operation.expense;

import lombok.Getter;

@Getter
public enum PaymentMethod {
  CASH("cash"),
  BANK_CARD("bankCard"),
  BANK_TRANSFER("bankTransfer"),
  BANK_CHECK("bankCheck"),
  ELECTRONIC_WALLET("electronicWallet"),
  ONLINE_BANK_TRANSFER("onlineBankTransfer"),
  PAYMENT_SYSTEM("paymentSystem"),
  CRYPTOCURRENCIES("cryptocurrencies"),
  CHECK("check"),
  BILL_OF_EXCHANGE("billOfExchange"),
  ANOTHER("another");

  private final String name;

  PaymentMethod(String name) {
    this.name = name;
  }

  public static PaymentMethod byName(String name) {
    for (PaymentMethod paymentMethod : PaymentMethod.values()) {
      if (paymentMethod.name.equals(name)) {
        return paymentMethod;
      }
    }
    throw new IllegalArgumentException("PaymentMethod with name: " + name + " not found");
  }
}
