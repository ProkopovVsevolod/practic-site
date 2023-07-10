package com.finance.budget.view.mapper;

import com.finance.budget.domain.operation.expense.ExpensePlan;
import com.finance.budget.view.dto.ListDto;
import com.finance.budget.view.dto.expense.plan.ExpensePlanCommonRequestDto;
import com.finance.budget.view.dto.expense.plan.ExpensePlanCommonResponseDto;
import com.finance.budget.view.mapper.enums.AmountEnumMapper;
import com.finance.budget.view.mapper.enums.ExpenseEnumMapper;
import com.finance.budget.view.mapper.enums.PeriodEnumMapper;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ExpensePlanMapper {
  private final IdMapper idMapper;
  private final AmountEnumMapper amountEnumMapper;
  private final PeriodEnumMapper periodEnumMapper;
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
      amountEnumMapper.convert(expensePlan.getLimit()),
      periodEnumMapper.convert(expensePlan.getPeriod()),
      expenseEnumMapper.convertToDtoList(expensePlan.getCategories())
    );
  }

  public ExpensePlan convertExpensePlanCommonRequestDtoToExpensePlan(Long userId,
                                                                     ExpensePlanCommonRequestDto expensePlanCommonRequestDto) {
    return new ExpensePlan(
      userId,
      amountEnumMapper.convert(expensePlanCommonRequestDto.getLimit()),
      periodEnumMapper.convert(expensePlanCommonRequestDto.getPeriod()),
      expenseEnumMapper.convertToDomainList(expensePlanCommonRequestDto.getCategories())
    );
  }
}
