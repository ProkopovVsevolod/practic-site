package com.finance.budget.service.contract;

import com.finance.budget.domain.CompositeId;
import com.finance.budget.domain.DependentByUserEntity;

import java.util.List;

public interface CrudService<T extends DependentByUserEntity> {
  T save(T entity);
  List<T> getSample(Long userId, Integer offset, Integer diapason);
  T find(CompositeId compositeId);
  T update(CompositeId compositeId, T entity);
  void delete(CompositeId compositeId);
}
