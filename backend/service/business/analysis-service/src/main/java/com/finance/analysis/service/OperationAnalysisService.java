package com.finance.analysis.service;

import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.Operation;
import com.finance.lib.budget.domain.entity.Period;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OperationAnalysisService {

  //TODO
  public List<Operation> operationsByPeriod(@Valid @NotNull CompositeId budgetCompositeId,
                                            @Valid @NotNull Period period) {
//    List<Operation> operations = new ArrayList<>();
//    operations.addAll(incomesByPeriod(budgetCompositeId, period));
//    operations.addAll(expensesByPeriod(budgetCompositeId, period));
//
//    return operations.stream()
//      .sorted(Comparator.comparing(Operation::getDateTime).reversed())
//      .toList();
    throw new NotImplementedException();
  }
}
