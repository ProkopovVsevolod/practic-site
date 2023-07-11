package com.finance.budget.view.mapper.enums;

import com.finance.budget.domain.amount.Amount;
import com.finance.budget.domain.amount.Currency;
import com.finance.budget.view.dto.amount.AmountDto;
import com.finance.budget.view.dto.amount.CurrencyDto;

public class AmountEnumMapper {
  public Currency convert(CurrencyDto currencyDto) {
    return Currency.byName(currencyDto.toName());
  }

  public CurrencyDto convert(Currency currency) {
    return CurrencyDto.createByName(currency.getName());
  }

  public AmountDto convert(Amount amount) {
    return new AmountDto(
      amount.getAmount(),
      convert(amount.getAmountCurrency())
    );
  }

  public Amount convert(AmountDto amountDto) {
    return new Amount(
      amountDto.getValue(),
      convert(amountDto.getCurrency())
    );
  }
}
