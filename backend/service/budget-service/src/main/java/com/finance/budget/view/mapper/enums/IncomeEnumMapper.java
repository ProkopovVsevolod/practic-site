package com.finance.budget.view.mapper.enums;

import com.finance.budget.domain.operation.income.IncomeCategory;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.income.IncomeCategoryDto;

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
