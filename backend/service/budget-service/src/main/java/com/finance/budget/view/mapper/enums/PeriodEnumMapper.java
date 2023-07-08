package com.finance.budget.view.mapper.enums;

import com.finance.budget.domain.Period;
import com.finance.budget.view.dto.PeriodDto;

public class PeriodEnumMapper {
  public Period convert(PeriodDto periodDto) {
    return Period.byName(periodDto.toName());
  }

  public PeriodDto convert(Period period) {
    return PeriodDto.createByName(period.getName());
  }
}
