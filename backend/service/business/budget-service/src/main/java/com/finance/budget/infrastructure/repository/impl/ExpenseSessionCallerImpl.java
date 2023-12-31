package com.finance.budget.infrastructure.repository.impl;

import com.finance.lib.budget.domain.entity.operation.expense.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseSessionCallerImpl extends AbstractSessionCaller<Expense> {
  public ExpenseSessionCallerImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<Expense> findSample(Long userId, Integer offset, Integer count) {
    String hql = "from Expense where compositeId.userId = :userId";
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery(hql, Expense.class)
        .setParameter("userId", userId)
        .setFirstResult(offset)
        .setMaxResults(count)
        .list();
    }
  }
}
