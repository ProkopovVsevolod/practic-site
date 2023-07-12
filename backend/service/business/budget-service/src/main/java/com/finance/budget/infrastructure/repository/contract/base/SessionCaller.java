package com.finance.budget.infrastructure.repository.contract.base;

import com.finance.lib.budget.domain.entity.DependentByUserEntity;

import java.util.List;

public interface SessionCaller<T extends DependentByUserEntity> {
  List<T> findSample(Long userId, Integer offset, Integer count);
}
