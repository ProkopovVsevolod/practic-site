package com.finance.budget.service.impl;

import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import com.finance.budget.service.contract.IncomePlanService;
import com.finance.lib.budget.domain.entity.operation.income.IncomePlan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IncomePlanServiceImpl extends DependentByUserCrudService<IncomePlan> implements IncomePlanService {
  public IncomePlanServiceImpl(DependentByUserRepository<IncomePlan> dependentByUserRepository,
                               SessionCaller<IncomePlan> sessionCaller) {
    super(dependentByUserRepository, sessionCaller);
  }
}
