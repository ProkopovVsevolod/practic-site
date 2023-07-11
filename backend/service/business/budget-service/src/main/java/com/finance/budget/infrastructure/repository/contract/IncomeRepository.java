package com.finance.budget.infrastructure.repository.contract;

import com.finance.budget.domain.operation.income.Income;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;

public interface IncomeRepository extends DependentByUserRepository<Income> { }