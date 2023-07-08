package com.finance.budget.infrastructure.repository.impl;

import com.finance.budget.domain.operation.income.Income;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncomeSessionCallerImpl implements SessionCaller<Income> {
  private final SessionFactory sessionFactory;

  @Autowired
  public IncomeSessionCallerImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Income> findSample(Long userId, Integer offset, Integer count) {
    String hql = "from Income where userId = :userId";
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery(hql, Income.class)
        .setParameter("userId", userId)
        .setFirstResult(offset)
        .setMaxResults(count)
        .list();
    }
  }
}
