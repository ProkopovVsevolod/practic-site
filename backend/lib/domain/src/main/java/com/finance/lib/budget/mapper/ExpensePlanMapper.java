package com.finance.lib.budget.mapper;

import com.finance.lib.budget.domain.entity.operation.expense.ExpensePlan;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.expense.plan.ExpensePlanCommonRequestDto;
import com.finance.lib.budget.dto.expense.plan.ExpensePlanCommonResponseDto;
import com.finance.lib.budget.mapper.enums.ExpenseEnumMapper;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ExpensePlanMapper {
  private final IdMapper idMapper;
  private final AmountMapper amountMapper;
  private final PeriodMapper periodMapper;
  private final ExpenseEnumMapper expenseEnumMapper;

  public ListDto<ExpensePlanCommonResponseDto> convertExpensePlanListToExpensePlanCommonResponseDtoList(Collection<ExpensePlan> expensePlanList,
                                                                                                        Integer offset,
                                                                                                        Integer diapason) {
    List<ExpensePlanCommonResponseDto> expensePlanCommonResponseDtos = expensePlanList.stream()
      .map(this::convertExpensePlanToExpensePlanCommonResponseDto)
      .toList();
    return new ListDto<>(expensePlanCommonResponseDtos, offset, diapason);
  }

  public ListDto<ExpensePlanCommonResponseDto> convertExpensePlanListToExpensePlanCommonResponseDtoList(Collection<ExpensePlan> expensePlanList) {
    List<ExpensePlanCommonResponseDto> expensePlanCommonResponseDtos = expensePlanList.stream()
      .map(this::convertExpensePlanToExpensePlanCommonResponseDto)
      .toList();
    return new ListDto<>(expensePlanCommonResponseDtos);
  }

  public ExpensePlanCommonResponseDto convertExpensePlanToExpensePlanCommonResponseDto(ExpensePlan expensePlan) {
    return new ExpensePlanCommonResponseDto(
      idMapper.convert(expensePlan.getCompositeId()),
      amountMapper.convert(expensePlan.getLimit()),
      periodMapper.convert(expensePlan.getPeriod()),
      expenseEnumMapper.convert(expensePlan.getCategory())
    );
  }

  public ExpensePlan convertExpensePlanCommonRequestDtoToExpensePlan(Long userId,
                                                                     ExpensePlanCommonRequestDto expensePlanCommonRequestDto) {
    return new ExpensePlan(
      userId,
      amountMapper.convert(expensePlanCommonRequestDto.getLimit()),
      expenseEnumMapper.convert(expensePlanCommonRequestDto.getCategory())
    );
  }

  public List<ExpensePlan> convertFromResponseDtoList(ListDto<ExpensePlanCommonResponseDto> listDto) {
    return listDto.getElements().stream()
      .map(this::convertFromResponseDto)
      .toList();
  }

  public ExpensePlan convertFromResponseDto(ExpensePlanCommonResponseDto dto) {
    return new ExpensePlan(
      dto.getCompositeId().getUserId(),
      amountMapper.convert(dto.getLimit()),
      expenseEnumMapper.convert(dto.getCategory())
    );
  }
}
