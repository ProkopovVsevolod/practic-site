package com.finance.lib.budget.mapper;

import com.finance.lib.budget.domain.entity.operation.income.Income;
import com.finance.lib.budget.domain.entity.operation.income.IncomeCategory;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.operation.income.IncomeCategoryDto;
import com.finance.lib.budget.dto.operation.income.IncomeCommonRequestDto;
import com.finance.lib.budget.dto.operation.income.IncomeCommonResponseDto;
import com.finance.lib.budget.mapper.enums.IncomeEnumMapper;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class IncomeMapper {
  private final IdMapper idMapper;
  private final IncomeEnumMapper incomeEnumMapper;
  private final AmountMapper amountMapper;

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
      Income.class.getSimpleName(),
      idMapper.convert(income.getCompositeId()),
      income.getName(),
      income.getDescription(),
      income.getDateTime(),
      amountMapper.convert(income.getAmount()),
      income.getSource(),
      incomeEnumMapper.convert(income.getIncomeCategory())
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
      amountMapper.convert(incomeCommonRequestDto.getAmount()),
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
      amountMapper.convert(incomeUpdateRequestDto.getAmount()),
      incomeUpdateRequestDto.getSource(),
      incomeEnumMapper.convert(incomeUpdateRequestDto.getIncomeCategory())
    );
  }

  public List<Income> convertListDtoToList(ListDto<IncomeCommonResponseDto> incomeCommonResponseDtoListDto) {
    return incomeCommonResponseDtoListDto.getElements().stream()
      .map(this::convertFromResponse)
      .toList();
  }

  public Income convertFromResponse(IncomeCommonResponseDto incomeCommonResponseDto) {
    return new Income(
      incomeCommonResponseDto.getCompositeId().getUserId(),
      incomeCommonResponseDto.getName(),
      incomeCommonResponseDto.getDescription(),
      amountMapper.convert(incomeCommonResponseDto.getAmount()),
      incomeCommonResponseDto.getSource(),
      incomeEnumMapper.convert(incomeCommonResponseDto.getIncomeCategory())
    );
  }
}
