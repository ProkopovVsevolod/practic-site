package com.finance.lib.budget.domain.entity;

import com.finance.lib.budget.domain.entity.converter.YearMonthAttributeConverter;
import com.finance.lib.budget.domain.entity.converter.YearMonthConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
public class Period implements Comparable<Period> {
  @NotNull
  @Convert(converter = YearMonthAttributeConverter.class)
  private YearMonth start;

  @NotNull
  private Duration duration;

  public Period(YearMonth start, Duration duration) {
    this.start = start;
    this.duration = duration;
  }

  public Period(Duration duration) {
    this.start = YearMonth.now();
    this.duration = duration;
  }

  @Override
  public int compareTo(Period o) {
    if (start == o.start) {
      return duration.compareTo(o.duration);
    } else {
      return start.compareTo(o.start);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Period period = (Period) o;
    return Objects.equals(start, period.start) && duration == period.duration;
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, duration);
  }

  public OffsetDateTime startDateTime() {
    return YearMonthConverter.offsetDateTimeByYearMonth(start);
  }

  public OffsetDateTime endDateTime() {
    return YearMonthConverter.offsetDateTimeByYearMonth(start.plusMonths(duration.getMonths()));
  }
}
