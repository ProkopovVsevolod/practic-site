package com.finance.budget.infrastructure.repository.impl;

import com.finance.lib.budget.domain.entity.Budget;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BudgetSessionCallerImpl extends AbstractSessionCaller<Budget> {
  @Autowired
  public BudgetSessionCallerImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<Budget> findSample(Long userId, Integer offset, Integer count) {
    String hql = "from Budget where compositeId.userId = :userId";
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery(hql, Budget.class)
        .setParameter("userId", userId)
        .setFirstResult(offset)
        .setMaxResults(count)
        .list();
    }
  }
}
