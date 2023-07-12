package com.finance.lib.budget.mapper;

import com.finance.lib.budget.domain.entity.Duration;
import com.finance.lib.budget.domain.entity.Period;
import com.finance.lib.budget.dto.DurationDto;
import com.finance.lib.budget.dto.PeriodDto;

import java.time.YearMonth;

public class PeriodMapper {
  public Period convert(PeriodDto periodDto) {
    int year = periodDto.getYear();
    int month = periodDto.getMonth();
    return new Period(
      YearMonth.of(year, month),
      Duration.byName(periodDto.getDuration().toName())
    );
  }

  public Period convertDurationToNowPeriod(DurationDto durationDto) {
    return new Period(Duration.byName(durationDto.toName()));
  }

  public PeriodDto convert(Period period) {
    YearMonth start = period.getStart();
    return new PeriodDto(
      start.getYear(),
      start.getMonthValue(),
      DurationDto.createByName(period.getDuration().getName())
    );
  }
}
