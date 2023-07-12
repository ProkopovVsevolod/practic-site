package com.finance.analysis.service;

import com.finance.analysis.infrastructure.external.BudgetServiceClient;
import com.finance.jwt.domain.OpenAccessToken;
import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.Operation;
import com.finance.lib.budget.domain.entity.Period;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationAnalysisService {
  private final BudgetServiceClient budgetServiceClient;

  public List<Operation> operationsByPeriod(@Valid @NotNull CompositeId id,
                                            @Valid @NotNull Period period,
                                            OpenAccessToken openAccessToken) {
    List<Operation> operations = new ArrayList<>();
    operations.addAll(budgetServiceClient.getBudgetIncomesByPeriod(id.getId(), period, openAccessToken));
    operations.addAll(budgetServiceClient.getBudgetExpensesByPeriod(id.getId(), period, openAccessToken));

    return operations.stream()
      .sorted(Comparator.comparing(Operation::getDateTime).reversed())
      .toList();
  }
}
