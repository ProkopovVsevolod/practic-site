package com.finance.budget.infrastructure.repository.contract;

import com.finance.budget.domain.Budget;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;

public interface BudgetRepository extends DependentByUserRepository<Budget, Long> { }