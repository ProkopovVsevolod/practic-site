package com.finance.budget.infrastructure.repository.impl;

import com.finance.lib.budget.domain.entity.operation.expense.ExpensePlan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpensePlanSessionCallerImpl extends AbstractSessionCaller<ExpensePlan> {
  public ExpensePlanSessionCallerImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<ExpensePlan> findSample(Long userId, Integer offset, Integer count) {
    String hql = "from ExpensePlan where compositeId.userId = :userId";
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery(hql, ExpensePlan.class)
        .setParameter("userId", userId)
        .setFirstResult(offset)
        .setMaxResults(count)
        .list();
    }
  }
}
