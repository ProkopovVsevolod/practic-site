package com.finance.lib.budget.domain.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.OffsetDateTime;
import java.time.YearMonth;

@Converter
public class YearMonthAttributeConverter implements AttributeConverter<YearMonth, OffsetDateTime> {

  @Override
  public OffsetDateTime convertToDatabaseColumn(YearMonth attribute) {
    if (attribute == null) {
      return null;
    }
    return YearMonthConverter.offsetDateTimeByYearMonth(attribute);
  }

  @Override
  public YearMonth convertToEntityAttribute(OffsetDateTime dbData) {
    if (dbData == null) {
      return null;
    }
    return YearMonth.of(dbData.getYear(), dbData.getMonth());
  }
}
