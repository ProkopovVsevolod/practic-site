package com.finance.budget.infrastructure.repository.impl;

import com.finance.budget.domain.operation.expense.Expense;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseSessionCallerImpl implements SessionCaller<Expense> {
  private final SessionFactory sessionFactory;

  @Autowired
  public ExpenseSessionCallerImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public List<Expense> findSample(Long userId, Integer offset, Integer count) {
    String hql = "from Expense where userId = :userId";
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery(hql, Expense.class)
        .setParameter("userId", userId)
        .setFirstResult(offset)
        .setMaxResults(count)
        .list();
    }
  }
}
