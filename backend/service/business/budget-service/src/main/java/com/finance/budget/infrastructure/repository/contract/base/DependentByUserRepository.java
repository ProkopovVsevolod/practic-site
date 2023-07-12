package com.finance.budget.infrastructure.repository.contract.base;

import com.finance.lib.budget.domain.entity.CompositeId;
import com.finance.lib.budget.domain.entity.DependentByUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DependentByUserRepository<T extends DependentByUserEntity> extends JpaRepository<T, CompositeId> {
}

