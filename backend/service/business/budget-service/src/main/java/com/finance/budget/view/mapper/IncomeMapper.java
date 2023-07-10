package com.finance.budget.view.mapper;

import com.finance.budget.domain.operation.income.Income;
import com.finance.budget.domain.operation.income.IncomeCategory;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.income.IncomeCategoryDto;
import com.finance.budget.view.dto.income.IncomeCommonRequestDto;
import com.finance.budget.view.dto.income.IncomeCommonResponseDto;
import com.finance.budget.view.mapper.enums.AmountEnumMapper;
import com.finance.budget.view.mapper.enums.IncomeEnumMapper;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class IncomeMapper {
  private final IdMapper idMapper;
  private final IncomeEnumMapper incomeEnumMapper;
  private final AmountEnumMapper amountEnumMapper;

  public ListDto<IncomeCommonResponseDto> convertIncomeListToIncomeCommonResponseDtoList(Collection<Income> incomeList, Integer offset, Integer diapason) {
    List<IncomeCommonResponseDto> incomeCommonResponseDtos = incomeList.stream()
      .map(this::convertIncomeToIncomeCommonResponseDto)
      .toList();
    return new ListDto<>(incomeCommonResponseDtos, offset, diapason);
  }

  public ListDto<IncomeCommonResponseDto> convertIncomeListToIncomeCommonResponseDtoList(Collection<Income> incomeList) {
    List<IncomeCommonResponseDto> incomeCommonResponseDtos = incomeList.stream()
      .map(this::convertIncomeToIncomeCommonResponseDto)
      .toList();
    return new ListDto<>(incomeCommonResponseDtos);
  }

  public IncomeCommonResponseDto convertIncomeToIncomeCommonResponseDto(Income income) {
    return new IncomeCommonResponseDto(
      idMapper.convert(income.getCompositeId()),
      income.getName(),
      income.getDescription(),
      income.getSource(),
      income.getDateTime(),
      incomeEnumMapper.convert(income.getIncomeCategory()),
      amountEnumMapper.convert(income.getAmount())
    );
  }

  public ListDto<IncomeCategoryDto> convertIncomeCategoryListToIncomeCategoryDtoList(List<IncomeCategory> incomeCategoryList, Integer offset, Integer diapason) {
    List<IncomeCategoryDto> incomeCategoryDtos = incomeCategoryList.stream()
      .map(incomeEnumMapper::convert)
      .toList();
    return new ListDto<>(incomeCategoryDtos, offset, diapason);
  }

  public Income convertIncomeCreateRequestDtoToIncome(Long userId,
                                                      IncomeCommonRequestDto incomeCommonRequestDto) {
    return new Income(
      userId,
      incomeCommonRequestDto.getName(),
      incomeCommonRequestDto.getDescription(),
      amountEnumMapper.convert(incomeCommonRequestDto.getAmount()),
      incomeCommonRequestDto.getSource(),
      incomeEnumMapper.convert(incomeCommonRequestDto.getIncomeCategory())
    );
  }


  public Income convertIncomeUpdateRequestDtoToIncome(Long userid,
                                                      IncomeCommonRequestDto incomeUpdateRequestDto) {
    return new Income(
      userid,
      incomeUpdateRequestDto.getName(),
      incomeUpdateRequestDto.getDescription(),
      amountEnumMapper.convert(incomeUpdateRequestDto.getAmount()),
      incomeUpdateRequestDto.getSource(),
      incomeEnumMapper.convert(incomeUpdateRequestDto.getIncomeCategory())
    );
  }
}
