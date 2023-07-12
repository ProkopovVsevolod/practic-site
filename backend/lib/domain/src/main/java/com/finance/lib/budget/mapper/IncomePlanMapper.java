package com.finance.lib.budget.mapper;

import com.finance.lib.budget.dto.income.plan.IncomePlanCommonRequestDto;
import com.finance.lib.budget.dto.income.plan.IncomePlanCommonResponseDto;
import com.finance.lib.budget.mapper.enums.IncomeEnumMapper;
import com.finance.lib.budget.domain.entity.operation.income.IncomePlan;
import com.finance.lib.budget.dto.ListDto;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class IncomePlanMapper {
  private final IdMapper idMapper;
  private final AmountMapper amountMapper;
  private final PeriodMapper periodMapper;
  private final IncomeEnumMapper incomeEnumMapper;

  public IncomePlan convertIncomeCommonRequestDtoToIncome(Long userId,
                                                          IncomePlanCommonRequestDto incomePlanCreateRequestDto) {
    return new IncomePlan(
      userId,
      amountMapper.convert(incomePlanCreateRequestDto.getLimit()),
      incomeEnumMapper.convert(incomePlanCreateRequestDto.getCategory())
    );
  }

  public IncomePlanCommonResponseDto convertIncomeToIncomePlanCommonResponseDto(IncomePlan incomePlan) {
    return new IncomePlanCommonResponseDto(
      idMapper.convert(incomePlan.getCompositeId()),
      amountMapper.convert(incomePlan.getLimit()),
      periodMapper.convert(incomePlan.getPeriod()),
      incomeEnumMapper.convert(incomePlan.getCategory())
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
      amountMapper.convert(incomePlan.getLimit()),
      periodMapper.convert(incomePlan.getPeriod()),
      incomeEnumMapper.convert(incomePlan.getCategory())
    );
  }

  public List<IncomePlan> convertFromResponseDtoList(ListDto<IncomePlanCommonResponseDto> listDto) {
    return listDto.getElements().stream()
      .map(this::convertFromResponseDto)
      .toList();
  }

  public IncomePlan convertFromResponseDto(IncomePlanCommonResponseDto dto) {
    return new IncomePlan(
      dto.getCompositeId().getUserId(),
      amountMapper.convert(dto.getLimit()),
      incomeEnumMapper.convert(dto.getCategory())
    );
  }
}
