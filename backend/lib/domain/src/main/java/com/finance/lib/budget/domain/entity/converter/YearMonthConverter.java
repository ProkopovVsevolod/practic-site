package com.finance.lib.budget.domain.entity.converter;

import java.time.*;

public class YearMonthConverter {
  public static OffsetDateTime offsetDateTimeByYearMonth(YearMonth yearMonth) {
    LocalDate firstDayOfMonth = yearMonth.atDay(1);
    ZonedDateTime startOfDay = firstDayOfMonth.atStartOfDay(ZoneOffset.UTC);
    return startOfDay.toOffsetDateTime();
  }
}


