package com.finance.budget.view.mapper.enums;

import com.finance.budget.domain.operation.income.IncomeCategory;
import com.finance.budget.view.dto.income.IncomeCategoryDto;
import org.springframework.stereotype.Component;

@Component
public class IncomeEnumMapper {
  public IncomeCategoryDto convert(IncomeCategory incomeCategory) {
    return IncomeCategoryDto.createByName(incomeCategory.getName());
  }

  public IncomeCategory convert(IncomeCategoryDto incomeCategoryDto) {
    return IncomeCategory.byName(incomeCategoryDto.toName());
  }
}
