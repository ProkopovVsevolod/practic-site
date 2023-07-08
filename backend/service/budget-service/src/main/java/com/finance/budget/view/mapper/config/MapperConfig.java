package com.finance.budget.view.mapper.config;

import com.finance.budget.view.mapper.*;
import com.finance.budget.view.mapper.enums.AmountEnumMapper;
import com.finance.budget.view.mapper.enums.ExpenseEnumMapper;
import com.finance.budget.view.mapper.enums.IncomeEnumMapper;
import com.finance.budget.view.mapper.enums.PeriodEnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
  @Bean
  public AmountEnumMapper amountEnumMapper() {
    return new AmountEnumMapper();
  }

  @Bean
  public ExpenseEnumMapper expenseEnumMapper() {
    return new ExpenseEnumMapper();
  }

  @Bean
  public IncomeEnumMapper incomeEnumMapper() {
    return new IncomeEnumMapper();
  }

  @Bean
  public PeriodEnumMapper periodEnumMapper() {
    return new PeriodEnumMapper();
  }

  @Bean
  public IdMapper idMapper() {
    return new IdMapper();
  }

  @Bean
  public IncomeMapper incomeMapper(IdMapper idMapper,
                                   IncomeEnumMapper incomeEnumMapper,
                                   AmountEnumMapper amountEnumMapper) {
    return new IncomeMapper(idMapper, incomeEnumMapper, amountEnumMapper);
  }

  @Bean
  public IncomePlanMapper incomePlanMapper(IdMapper idMapper,
                                           AmountEnumMapper amountEnumMapper,
                                           PeriodEnumMapper periodEnumMapper,
                                           IncomeEnumMapper incomeEnumMapper) {
    return new IncomePlanMapper(idMapper, amountEnumMapper, periodEnumMapper, incomeEnumMapper);
  }

  @Bean
  public ExpenseMapper expenseMapper(IdMapper idMapper,
                                     ExpenseEnumMapper expenseEnumMapper,
                                     AmountEnumMapper amountEnumMapper) {
    return new ExpenseMapper(idMapper, expenseEnumMapper, amountEnumMapper);
  }

  @Bean
  public ExpensePlanMapper expensePlanMapper(IdMapper idMapper,
                                             AmountEnumMapper amountEnumMapper,
                                             PeriodEnumMapper periodEnumMapper,
                                             ExpenseEnumMapper expenseEnumMapper) {
    return new ExpensePlanMapper(idMapper, amountEnumMapper, periodEnumMapper, expenseEnumMapper);
  }

  @Bean
  public BudgetMapper budgetMapper(IdMapper idMapper,
                                   AmountEnumMapper amountEnumMapper,
                                   PeriodEnumMapper periodEnumMapper,
                                   IncomeMapper incomeMapper,
                                   IncomePlanMapper incomePlanMapper,
                                   ExpenseMapper expenseMapper,
                                   ExpensePlanMapper expensePlanMapper) {
    return new BudgetMapper(
      idMapper,
      amountEnumMapper,
      periodEnumMapper,
      incomeMapper,
      incomePlanMapper,
      expenseMapper,
      expensePlanMapper
    );
  }
}
