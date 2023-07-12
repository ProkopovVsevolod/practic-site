package com.finance.lib.budget.config;

import com.finance.lib.budget.mapper.*;
import com.finance.lib.budget.mapper.enums.ExpenseEnumMapper;
import com.finance.lib.budget.mapper.enums.IncomeEnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
  @Bean
  public AmountMapper amountMapper() {
    return new AmountMapper();
  }

  @Bean
  public IdMapper idMapper() {
    return new IdMapper();
  }

  @Bean
  public PeriodMapper periodMapper() {
    return new PeriodMapper();
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
  public PeriodMapper periodEnumMapper() {
    return new PeriodMapper();
  }

  @Bean
  public IncomeMapper incomeMapper(IdMapper idMapper,
                                   IncomeEnumMapper incomeEnumMapper,
                                   AmountMapper amountMapper) {
    return new IncomeMapper(idMapper, incomeEnumMapper, amountMapper);
  }

  @Bean
  public IncomePlanMapper incomePlanMapper(IdMapper idMapper,
                                           AmountMapper amountMapper,
                                           PeriodMapper periodMapper,
                                           IncomeEnumMapper incomeEnumMapper) {
    return new IncomePlanMapper(idMapper, amountMapper, periodMapper, incomeEnumMapper);
  }

  @Bean
  public ExpenseMapper expenseMapper(IdMapper idMapper,
                                     ExpenseEnumMapper expenseEnumMapper,
                                     AmountMapper amountMapper) {
    return new ExpenseMapper(idMapper, expenseEnumMapper, amountMapper);
  }

  @Bean
  public ExpensePlanMapper expensePlanMapper(IdMapper idMapper,
                                             AmountMapper amountMapper,
                                             PeriodMapper periodMapper,
                                             ExpenseEnumMapper expenseEnumMapper) {
    return new ExpensePlanMapper(idMapper, amountMapper, periodMapper, expenseEnumMapper);
  }

  @Bean
  public BudgetMapper budgetMapper(IdMapper idMapper,
                                   AmountMapper amountMapper,
                                   PeriodMapper periodMapper,
                                   IncomeMapper incomeMapper,
                                   IncomePlanMapper incomePlanMapper,
                                   ExpenseMapper expenseMapper,
                                   ExpensePlanMapper expensePlanMapper) {
    return new BudgetMapper(
      idMapper,
      amountMapper,
      periodMapper,
      incomeMapper,
      incomePlanMapper,
      expenseMapper,
      expensePlanMapper
    );
  }
}
