package com.finance.budget.service.impl;

import com.finance.budget.domain.operation.expense.ExpensePlan;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import com.finance.budget.service.contract.ExpensePlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpensePlanServiceImpl extends DependentByUserCrudService<ExpensePlan> implements ExpensePlanService {
  public ExpensePlanServiceImpl(DependentByUserRepository<ExpensePlan> dependentByUserRepository,
                                SessionCaller<ExpensePlan> sessionCaller) {
    super(dependentByUserRepository, sessionCaller);
  }
}
