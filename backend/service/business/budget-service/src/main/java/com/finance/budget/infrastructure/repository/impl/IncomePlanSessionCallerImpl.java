package com.finance.budget.infrastructure.repository.impl;

import com.finance.lib.budget.domain.entity.operation.income.IncomePlan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncomePlanSessionCallerImpl extends AbstractSessionCaller<IncomePlan> {
  public IncomePlanSessionCallerImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<IncomePlan> findSample(Long userId, Integer offset, Integer count) {
    String hql = "from IncomePlan where compositeId.userId = :userId";
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery(hql, IncomePlan.class)
        .setParameter("userId", userId)
        .setFirstResult(offset)
        .setMaxResults(count)
        .list();
    }
  }
}
