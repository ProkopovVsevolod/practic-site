package com.finance.budget.service.impl;

import com.finance.budget.domain.Budget;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import com.finance.budget.service.contract.BudgetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BudgetServiceImpl extends DependentByUserCrudService<Budget, Long> implements BudgetService {
  public BudgetServiceImpl(DependentByUserRepository<Budget, Long> dependentByUserRepository,
                           SessionCaller<Budget> sessionCaller) {
    super(dependentByUserRepository, sessionCaller);
  }
}
