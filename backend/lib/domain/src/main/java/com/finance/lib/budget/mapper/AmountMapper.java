package com.finance.lib.budget.mapper;

import com.finance.lib.budget.domain.entity.amount.Amount;
import com.finance.lib.budget.domain.entity.amount.Currency;
import com.finance.lib.budget.dto.amount.AmountDto;
import com.finance.lib.budget.dto.amount.CurrencyDto;

public class AmountMapper {
  public Currency convert(CurrencyDto currencyDto) {
    return Currency.byName(currencyDto.toName());
  }

  public CurrencyDto convert(Currency currency) {
    return CurrencyDto.createByName(currency.getName());
  }

  public AmountDto convert(Amount amount) {
    return new AmountDto(
      amount.getValue(),
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
