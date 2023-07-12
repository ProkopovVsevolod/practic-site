package com.finance.lib.budget.domain.entity.amount;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
public class Amount implements Comparable<Amount>{
  @NotNull
  private BigDecimal value;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Currency amountCurrency;

  public Amount() {
  }

  public Amount(BigDecimal value, Currency amountCurrency) {
    this.value = value;
    this.amountCurrency = amountCurrency;
  }

  public static class Builder {
    protected BigDecimal amount;
    protected Currency amountCurrency;

    public Builder amount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }

    public Builder amountCurrency(Currency amountCurrency) {
      this.amountCurrency = amountCurrency;
      return this;
    }

    public Amount build() {
      return new Amount(amount, amountCurrency);
    }
  }

  @Override
  public int compareTo(Amount amount) {
    if (amountCurrency.equals(amount.getAmountCurrency())) {
      return value.compareTo(amount.getValue());
    } else {
      return amountCurrency.compareTo(amount.getAmountCurrency());
    }
  }

  public Amount increment(Amount amount) {
    if (!amountCurrency.equals(amount.amountCurrency)) {
      throw new IllegalArgumentException("Amount currencies not equals");
    }
    return new Amount(value.add(amount.getValue()), amountCurrency);
  }

  public Amount decrement(Amount amount) {
    if (!amountCurrency.equals(amount.amountCurrency)) {
      throw new IllegalArgumentException("Amount currencies not equals");
    }
    return new Amount(value.subtract(amount.value), amountCurrency);
  }

  public static Amount empty(Currency currency) {
    return new Amount(new BigDecimal(0), currency);
  }
}
