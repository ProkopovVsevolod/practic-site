package com.finance.budget.infrastructure.repository.impl;

import com.finance.budget.domain.operation.income.Income;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncomeSessionCallerImpl extends AbstractSessionCaller<Income, Long> {
  public IncomeSessionCallerImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
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
