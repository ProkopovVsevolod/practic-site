package com.finance.budget.view.mapper;

import com.finance.budget.domain.operation.income.IncomePlan;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.income.plan.IncomePlanCommonRequestDto;
import com.finance.budget.view.dto.income.plan.IncomePlanCommonResponseDto;
import com.finance.budget.view.mapper.enums.AmountEnumMapper;
import com.finance.budget.view.mapper.enums.IncomeEnumMapper;
import com.finance.budget.view.mapper.enums.PeriodEnumMapper;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class IncomePlanMapper {
  private final IdMapper idMapper;
  private final AmountEnumMapper amountEnumMapper;
  private final PeriodEnumMapper periodEnumMapper;
  private final IncomeEnumMapper incomeEnumMapper;

  public IncomePlan convertIncomeCommonRequestDtoToIncome(Long userId,
                                                          IncomePlanCommonRequestDto incomePlanCreateRequestDto) {
    return new IncomePlan(
      userId,
      amountEnumMapper.convert(incomePlanCreateRequestDto.getLimit()),
      periodEnumMapper.convert(incomePlanCreateRequestDto.getPeriod()),
      incomeEnumMapper.convertToDomainList(incomePlanCreateRequestDto.getCategories())
    );
  }

  public IncomePlanCommonResponseDto convertIncomeToIncomePlanCommonResponseDto(IncomePlan incomePlan) {
    return new IncomePlanCommonResponseDto(
      idMapper.convert(incomePlan.getCompositeId()),
      amountEnumMapper.convert(incomePlan.getLimit()),
      periodEnumMapper.convert(incomePlan.getPeriod()),
      incomeEnumMapper.convertToDtoList(incomePlan.getCategories())
    );
  }

  public ListDto<IncomePlanCommonResponseDto> convertIncomePlanListToIncomePlanCommonResponseDtoList(Collection<IncomePlan> incomePlanList,
                                                                                                     Integer offset,
                                                                                                     Integer diapason) {
    List<IncomePlanCommonResponseDto> incomePlanCommonResponseDtos = incomePlanList.stream()
      .map(this::convertIncomePlanToIncomePlanCommonResponseDto)
      .toList();
    return new ListDto<>(incomePlanCommonResponseDtos, offset, diapason);
  }

  public ListDto<IncomePlanCommonResponseDto> convertIncomePlanListToIncomePlanCommonResponseDtoList(Collection<IncomePlan> incomePlanList) {
    List<IncomePlanCommonResponseDto> incomePlanCommonResponseDtos = incomePlanList.stream()
      .map(this::convertIncomePlanToIncomePlanCommonResponseDto)
      .toList();
    return new ListDto<>(incomePlanCommonResponseDtos);
  }

  public IncomePlanCommonResponseDto convertIncomePlanToIncomePlanCommonResponseDto(IncomePlan incomePlan) {
    return new IncomePlanCommonResponseDto(
      idMapper.convert(incomePlan.getCompositeId()),
      amountEnumMapper.convert(incomePlan.getLimit()),
      periodEnumMapper.convert(incomePlan.getPeriod()),
      incomeEnumMapper.convertToDtoList(incomePlan.getCategories())
    );
  }
}
