package com.finance.budget.service.impl;

import com.finance.budget.domain.CompositeId;
import com.finance.budget.domain.DependentByUserEntity;
import com.finance.budget.infrastructure.repository.contract.base.DependentByUserRepository;
import com.finance.budget.infrastructure.repository.contract.base.SessionCaller;
import com.finance.budget.service.contract.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
@Transactional
public abstract class DependentByUserCrudService<T extends DependentByUserEntity> implements CrudService<T> {
  private final DependentByUserRepository<T> dependentByUserRepository;
  private final SessionCaller<T> sessionCaller;
  private final String genericClassName;

  public DependentByUserCrudService(DependentByUserRepository<T> dependentByUserRepository,
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
  public T find(CompositeId id) {
    return dependentByUserRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException(genericClassName + " with id: " + id + " not found"));
  }

  @Override
  public T update(CompositeId id, T next) {
    T present = find(id);
     next.getIncludeSuperclassDeclaredFields()
      .forEach(field -> {
        if (!field.getType().equals(CompositeId.class)) {
          boolean isAccessible = field.canAccess(next);
          field.setAccessible(true);
          try {
            Object value = field.get(next);
            if (value != null) {
              field.set(present, value);
            }
          } catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
          field.setAccessible(isAccessible);
        }
      });
    return dependentByUserRepository.save(present);
  }

  @Override
  public void delete(CompositeId id) {
    find(id);
    dependentByUserRepository.deleteById(id);
  }
}
