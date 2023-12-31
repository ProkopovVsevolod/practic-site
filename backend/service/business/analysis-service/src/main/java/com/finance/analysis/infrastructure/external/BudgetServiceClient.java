package com.finance.analysis.infrastructure.external;

import com.finance.jwt.domain.OpenAccessToken;
import com.finance.jwt.domain.token.TokenMetadata;
import com.finance.lib.budget.domain.entity.Period;
import com.finance.lib.budget.domain.entity.amount.Amount;
import com.finance.lib.budget.domain.entity.amount.Currency;
import com.finance.lib.budget.domain.entity.operation.expense.Expense;
import com.finance.lib.budget.domain.entity.operation.expense.ExpenseCategory;
import com.finance.lib.budget.domain.entity.operation.expense.ExpensePlan;
import com.finance.lib.budget.domain.entity.operation.income.Income;
import com.finance.lib.budget.domain.entity.operation.income.IncomeCategory;
import com.finance.lib.budget.domain.entity.operation.income.IncomePlan;
import com.finance.lib.budget.dto.ListDto;
import com.finance.lib.budget.dto.amount.AmountDto;
import com.finance.lib.budget.dto.amount.CurrencyDto;
import com.finance.lib.budget.dto.operation.expense.ExpenseCommonResponseDto;
import com.finance.lib.budget.dto.operation.expense.plan.ExpensePlanCommonResponseDto;
import com.finance.lib.budget.dto.operation.income.IncomeCommonResponseDto;
import com.finance.lib.budget.dto.operation.income.plan.IncomePlanCommonResponseDto;
import com.finance.lib.budget.mapper.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.YearMonth;
import java.util.List;


@Component
public class BudgetServiceClient {
  private final IncomeMapper incomeMapper;
  private final ExpenseMapper expenseMapper;
  private final IncomePlanMapper incomePlanMapper;
  private final ExpensePlanMapper expensePlanMapper;
  private final AmountMapper amountMapper;
  private final WebClient client;

  public BudgetServiceClient(IncomeMapper incomeMapper,
                             ExpenseMapper expenseMapper,
                             IncomePlanMapper incomePlanMapper,
                             ExpensePlanMapper expensePlanMapper,
                             AmountMapper amountMapper,
                             WebClient.Builder clientBuilder) {
    this.incomeMapper = incomeMapper;
    this.expenseMapper = expenseMapper;
    this.amountMapper = amountMapper;
    this.incomePlanMapper = incomePlanMapper;
    this.expensePlanMapper = expensePlanMapper;
    this.client = clientBuilder.build();
  }

  private static class ListDtoIncomeCommonResponseDtoTypeReference extends ParameterizedTypeReference<ListDto<IncomeCommonResponseDto>> { }
  private static class ListDtoExpenseCommonResponseDtoTypeReference extends ParameterizedTypeReference<ListDto<ExpenseCommonResponseDto>> { }

  public Currency getBudgetCurrency(Long budgetId, OpenAccessToken openAccessToken) {
    CurrencyDto dto = client.get()
      .uri("api/v1/budgets/" + budgetId + "/currency")
      .header(TokenMetadata.ACCESS.getHeader(), "Bearer " + openAccessToken.getBody())
      .retrieve()
      .bodyToMono(CurrencyDto.class)
      .blockOptional()
      .orElseThrow(() -> new IllegalArgumentException("Cannot get currency by budgetId: " + budgetId));
    return amountMapper.convert(dto);
  }

  public List<Income> getBudgetIncomesByPeriod(Long budgetId, Period period, OpenAccessToken openAccessToken) {
    YearMonth start = period.getStart();
    ListDto<IncomeCommonResponseDto> listDto = client.get()
      .uri("/api/v1/budgets/" + budgetId + "/incomes/" + start.getYear() + "-" + start.getMonthValue() + ":" + period.getDuration().getName())
      .header(TokenMetadata.ACCESS.getHeader(), "Bearer " + openAccessToken.getBody())
      .retrieve()
      .bodyToMono(new ListDtoIncomeCommonResponseDtoTypeReference())
      .blockOptional()
      .orElseThrow(() -> new IllegalArgumentException("Cannot get incomes by budgetId: " + budgetId));
    return incomeMapper.convertListDtoToList(listDto);
  }

  public List<Expense> getBudgetExpensesByPeriod(Long budgetId, Period period, OpenAccessToken openAccessToken) {
    YearMonth start = period.getStart();
    ListDto<ExpenseCommonResponseDto> listDto = client.get()
      .uri("/api/v1/budgets/" + budgetId + "/expenses/" + start.getYear() + "-" + start.getMonthValue() + ":" + period.getDuration().getName())
      .header(TokenMetadata.ACCESS.getHeader(), "Bearer " + openAccessToken.getBody())
      .retrieve()
      .bodyToMono(new ListDtoExpenseCommonResponseDtoTypeReference())
      .blockOptional()
      .orElseThrow(() -> new IllegalArgumentException("Cannot get expenses by budgetId: " + budgetId));
    return expenseMapper.convertListDtoToList(listDto);
  }

  public Amount getBalance(Long budgetId, OpenAccessToken openAccessToken) {
    AmountDto amountDto = client.get()
      .uri("/api/v1/budgets/" + budgetId + "/balance")
      .header(TokenMetadata.ACCESS.getHeader(), "Bearer " + openAccessToken.getBody())
      .retrieve()
      .bodyToMono(AmountDto.class)
      .blockOptional()
      .orElseThrow(() -> new IllegalArgumentException("Cannot get balance by budgetId: " + budgetId));
    return amountMapper.convert(amountDto);
  }

  public IncomePlan getIncomePlan(Long budgetId,
                                  Period period,
                                  IncomeCategory category,
                                  OpenAccessToken openAccessToken) {
    YearMonth start = period.getStart();
    IncomePlanCommonResponseDto dto = client.get()
      .uri("/api/v1/budgets/" + budgetId + "/income-plans/" + start.getYear() + "-" + start.getMonthValue() + "/" + category.getName())
      .header(TokenMetadata.ACCESS.getHeader(), "Bearer " + openAccessToken.getBody())
      .retrieve()
      .bodyToMono(IncomePlanCommonResponseDto.class)
      .blockOptional()
      .orElseThrow(() -> new IllegalArgumentException("Cannot get income plans by budgetId: " + budgetId));
    return incomePlanMapper.convertFromResponseDto(dto);
  }

  public ExpensePlan getExpensePlan(Long budgetId,
                                    Period period,
                                    ExpenseCategory category,
                                    OpenAccessToken openAccessToken) {
    YearMonth start = period.getStart();
    ExpensePlanCommonResponseDto dto = client.get()
      .uri("/api/v1/budgets/" + budgetId + "/expense-plans/" + start.getYear() + "-" + start.getMonthValue() + "/" + category.getName())
      .header(TokenMetadata.ACCESS.getHeader(), "Bearer " + openAccessToken.getBody())
      .retrieve()
      .bodyToMono(ExpensePlanCommonResponseDto.class)
      .blockOptional()
      .orElseThrow(() -> new IllegalArgumentException("Cannot get expense plans by budgetId: " + budgetId));
    return expensePlanMapper.convertFromResponseDto(dto);
  }
}
