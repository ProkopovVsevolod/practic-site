package com.finance.budget.service.impl;

import com.finance.budget.domain.operation.income.Income;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import com.finance.budget.service.contract.IncomeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IncomeServiceImpl extends DependentByUserCrudService<Income> implements IncomeService {
  public IncomeServiceImpl(DependentByUserRepository<Income> dependentByUserRepository,
                           SessionCaller<Income> sessionCaller) {
    super(dependentByUserRepository, sessionCaller);
  }
}
