package com.finance.budget.infrastructure.repository.contract;

import com.finance.lib.budget.domain.entity.operation.expense.ExpensePlan;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;

public interface ExpensePlanRepository extends DependentByUserRepository<ExpensePlan> { }
