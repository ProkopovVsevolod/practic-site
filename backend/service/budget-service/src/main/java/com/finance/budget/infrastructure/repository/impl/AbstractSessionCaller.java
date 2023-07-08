package com.finance.budget.infrastructure.repository.impl;

import com.finance.budget.domain.DependentByUserEntity;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractSessionCaller<T extends DependentByUserEntity<ID>, ID extends Serializable> implements SessionCaller<T> {
  protected final SessionFactory sessionFactory;

  public AbstractSessionCaller(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public abstract List<T> findSample(Long userId, Integer offset, Integer count);
}
