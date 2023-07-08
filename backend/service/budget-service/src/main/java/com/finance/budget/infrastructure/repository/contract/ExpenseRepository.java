package com.finance.budget.infrastructure.repository.contract;

import com.finance.budget.domain.operation.expense.Expense;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;

public interface ExpenseRepository extends DependentByUserRepository<Expense> { }