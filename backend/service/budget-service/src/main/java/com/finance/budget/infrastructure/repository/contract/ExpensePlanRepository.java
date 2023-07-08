package com.finance.budget.infrastructure.repository.contract;

import com.finance.budget.domain.operation.expense.ExpensePlan;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;

public interface ExpensePlanRepository extends DependentByUserRepository<ExpensePlan> { }
