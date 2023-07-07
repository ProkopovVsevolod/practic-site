package com.finance.budget.service.impl;

import com.finance.budget.domain.DependentByUserEntity;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import com.finance.budget.service.contract.CrudService;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class DependentByUserCrudService<T extends DependentByUserEntity<ID>, ID extends Serializable> implements CrudService<T, ID> {
  private final DependentByUserRepository<T, ID> dependentByUserRepository;
  private final SessionCaller<T> sessionCaller;
  private final String genericClassName;

  public DependentByUserCrudService(DependentByUserRepository<T, ID> dependentByUserRepository,
                                    SessionCaller<T> sessionCaller) {
    this.dependentByUserRepository = dependentByUserRepository;
    this.sessionCaller = sessionCaller;

    ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
    Class<?> genericClass = (Class<?>) paramType.getActualTypeArguments()[0];
    genericClassName = genericClass.getSimpleName();
  }

  @Override
  public T save(T entity) {
    return dependentByUserRepository.save(entity);
  }

  @Override
  public List<T> getSample(Long userId, Integer offset, Integer diapason) {
    if (offset == null) offset = 0;
    if (diapason == null) diapason = Integer.MAX_VALUE;
    return sessionCaller.findSample(userId, offset, diapason);
  }

  @Override
  public T update(ID id, T entity) {
    Long userId = entity.getUserId();
    dependentByUserRepository.findByIdAndUserId(id, userId)
      .orElseThrow(() -> {
        var message = genericClassName + " with [userId:id]: [" + userId + ":" + id + "] not found";
        return new IllegalArgumentException(message);
      });

    entity.setId(id);
    return dependentByUserRepository.save(entity);
  }

  @Override
  public void delete(Long userId, ID id) {
    dependentByUserRepository.findById(id)
      .orElseThrow(() -> {
        var message =  genericClassName + " with [userId:incomeId]: [" + userId + ":" + id + "] not found";
        return new IllegalArgumentException(message);
      });

    dependentByUserRepository.deleteById(id);
  }
}
