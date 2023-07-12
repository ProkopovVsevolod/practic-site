package com.finance.budget.validation;

import com.finance.budget.infrastructure.repository.contract.BudgetRepository;
import com.finance.lib.budget.domain.entity.CompositeId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class BudgetValidationService {
  private final BudgetRepository budgetRepository;

  public void shouldPresent(@Valid @NotNull CompositeId budgetCompositeId) {
    if (!budgetRepository.existsById(budgetCompositeId)) {
      throw new IllegalArgumentException("Budget not found by id: " + budgetCompositeId);
    }
  }
}
