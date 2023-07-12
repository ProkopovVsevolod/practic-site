package com.finance.budget.infrastructure.repository.impl;

import com.finance.lib.budget.domain.entity.DependentByUserEntity;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class AbstractSessionCaller<T extends DependentByUserEntity> implements SessionCaller<T> {
  protected final SessionFactory sessionFactory;

  public AbstractSessionCaller(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public abstract List<T> findSample(Long userId, Integer offset, Integer count);
}
