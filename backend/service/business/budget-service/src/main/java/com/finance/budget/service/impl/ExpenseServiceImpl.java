package com.finance.budget.service.impl;

import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import com.finance.budget.service.contract.ExpenseService;
import com.finance.lib.budget.domain.entity.operation.expense.Expense;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpenseServiceImpl extends DependentByUserCrudService<Expense> implements ExpenseService {
  public ExpenseServiceImpl(DependentByUserRepository<Expense> dependentByUserRepository,
                            SessionCaller<Expense> sessionCaller) {
    super(dependentByUserRepository, sessionCaller);
  }
}
