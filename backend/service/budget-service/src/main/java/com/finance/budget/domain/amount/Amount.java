package com.finance.budget.domain.amount;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
public class Amount {
  private BigDecimal amount;

  @Enumerated
  private Currency amountCurrency;

  public Amount() {
  }

  public Amount(BigDecimal amount, Currency amountCurrency) {
    this.amount = amount;
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
}
