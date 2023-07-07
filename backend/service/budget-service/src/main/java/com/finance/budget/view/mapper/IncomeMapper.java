package com.finance.budget.view.mapper;

import com.finance.budget.domain.operation.income.Income;
import com.finance.budget.domain.operation.income.IncomeCategory;
import com.finance.budget.domain.operation.income.IncomePlan;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.income.*;
import com.finance.budget.view.mapper.enums.AmountEnumMapper;
import com.finance.budget.view.mapper.enums.IncomeEnumMapper;
import com.finance.budget.view.mapper.enums.PeriodEnumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncomeMapper {
  private final IncomeEnumMapper incomeEnumMapper;
  private final AmountEnumMapper amountEnumMapper;
  private final PeriodEnumMapper periodEnumMapper;

  @Autowired
  public IncomeMapper(IncomeEnumMapper incomeEnumMapper,
                      AmountEnumMapper amountEnumMapper,
                      PeriodEnumMapper periodEnumMapper) {
    this.incomeEnumMapper = incomeEnumMapper;
    this.amountEnumMapper = amountEnumMapper;
    this.periodEnumMapper = periodEnumMapper;
  }

  public ListDto<IncomeCommonResponseDto> convertIncomeListToIncomeCommonResponseDtoList(List<Income> incomeList, Integer offset, Integer diapason) {
    List<IncomeCommonResponseDto> incomeCommonResponseDtos = incomeList.stream()
      .map(this::convertIncomeToIncomeCommonResponseDto)
      .toList();
    return new ListDto<>(incomeCommonResponseDtos, offset, diapason);
  }

  public IncomeCommonResponseDto convertIncomeToIncomeCommonResponseDto(Income income) {
    return new IncomeCommonResponseDto(
      income.getId(),
      income.getName(),
      income.getDescription(),
      income.getSource(),
      income.getDateTime(),
      incomeEnumMapper.convert(income.getIncomeCategory()),
      amountEnumMapper.convert(income.getAmount()),
      income.getUserId()
    );
  }

  public ListDto<IncomePlanCommonResponseDto> convertIncomePlanListToIncomePlanCommonResponseDtoList(List<IncomePlan> incomePlanList,
                                                                                                     Integer offset,
                                                                                                     Integer diapason) {
    List<IncomePlanCommonResponseDto> incomePlanCommonResponseDtos = incomePlanList.stream()
      .map(this::convertIncomePlanToIncomePlanCommonResponseDto)
      .toList();
    return new ListDto<>(incomePlanCommonResponseDtos, offset, diapason);
  }

  public IncomePlanCommonResponseDto convertIncomePlanToIncomePlanCommonResponseDto(IncomePlan incomePlan) {
    return new IncomePlanCommonResponseDto(
      incomePlan.getId(),
      amountEnumMapper.convert(incomePlan.getLimit()),
      periodEnumMapper.convert(incomePlan.getPeriod()),
      convertIncomeCategoryListToIncomeCategoryDtoList(incomePlan.getCategories(), 0, incomePlan.getCategories().size())
    );
  }

  public ListDto<IncomeCategoryDto> convertIncomeCategoryListToIncomeCategoryDtoList(List<IncomeCategory> incomeCategoryList, Integer offset, Integer diapason) {
    List<IncomeCategoryDto> incomeCategoryDtos = incomeCategoryList.stream()
      .map(incomeEnumMapper::convert)
      .toList();
    return new ListDto<>(incomeCategoryDtos, offset, diapason);
  }

  public Income convertIncomeCreateRequestDtoToIncome(Long userId,
                                                      IncomeCreateRequestDto incomeCreateRequestDto) {
    return new Income(
      userId,
      incomeCreateRequestDto.getName(),
      incomeCreateRequestDto.getDescription(),
      amountEnumMapper.convert(incomeCreateRequestDto.getAmount()),
      incomeCreateRequestDto.getSource(),
      incomeEnumMapper.convert(incomeCreateRequestDto.getIncomeCategory())
    );
  }

  public Income convertIncomeUpdateRequestDtoToIncome(Long userid,
                                                      IncomeUpdateRequestDto incomeUpdateRequestDto) {
    return new Income(
      userid,
      incomeUpdateRequestDto.getName(),
      incomeUpdateRequestDto.getDescription(),
      amountEnumMapper.convert(incomeUpdateRequestDto.getAmount()),
      incomeUpdateRequestDto.getSource(),
      incomeEnumMapper.convert(incomeUpdateRequestDto.getIncomeCategory())
    );
  }

  public List<IncomePlan> convertIncomePlanCommonRequestDtoListToIncomePlanList(List<IncomePlanCommonRequestDto> incomePlanCommonRequestDtos) {
    return incomePlanCommonRequestDtos.stream()
      .map(this::convertIncomePlanCommonRequestDtoToIncomePlan)
      .toList();
  }

  public IncomePlan convertIncomePlanCommonRequestDtoToIncomePlan(IncomePlanCommonRequestDto incomePlanCommonRequestDto) {
    return new IncomePlan(
      amountEnumMapper.convert(incomePlanCommonRequestDto.getLimit()),
      periodEnumMapper.convert(incomePlanCommonRequestDto.getPeriod()),
      incomePlanCommonRequestDto.getCategories().stream().map(incomeEnumMapper::convert).toList()
    );
  }
}
