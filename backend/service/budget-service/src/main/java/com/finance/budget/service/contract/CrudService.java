package com.finance.budget.service.contract;

import com.finance.budget.domain.DependentByUserEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T extends DependentByUserEntity<ID>, ID extends Serializable> {
  T save(T entity);

  List<T> getSample(Long userId, Integer offset, Integer diapason);

  T update(ID id, T entity);

  void delete(Long userId, ID id);
}
