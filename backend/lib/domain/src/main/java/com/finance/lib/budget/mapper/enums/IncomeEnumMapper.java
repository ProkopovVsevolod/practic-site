package com.finance.lib.budget.mapper.enums;

import com.finance.lib.budget.domain.entity.operation.income.IncomeCategory;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.operation.income.IncomeCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class IncomeEnumMapper {
  public ListDto<IncomeCategoryDto> convertToDtoList(List<IncomeCategory> incomeCategoryList,
                                                     Integer offset,
                                                     Integer diapason) {
    List<IncomeCategoryDto> incomeCategoryDtos = incomeCategoryList.stream()
      .map(this::convert)
      .toList();
    return new ListDto<>(incomeCategoryDtos, offset, diapason);
  }

  public ListDto<IncomeCategoryDto> convertToDtoList(List<IncomeCategory> incomeCategoryList) {
    List<IncomeCategoryDto> incomeCategoryDtos = incomeCategoryList.stream()
      .map(this::convert)
      .toList();
    return new ListDto<>(incomeCategoryDtos);
  }

  public IncomeCategoryDto convert(IncomeCategory incomeCategory) {
    return IncomeCategoryDto.createByName(incomeCategory.getName());
  }

  public List<IncomeCategory> convertToDomainList(List<IncomeCategoryDto> incomeCategoryDtoList) {
    return new ArrayList<>(incomeCategoryDtoList.stream()
      .map(this::convert)
      .toList());
  }

  public IncomeCategory convert(IncomeCategoryDto incomeCategoryDto) {
    return IncomeCategory.byName(incomeCategoryDto.toName());
  }
}
