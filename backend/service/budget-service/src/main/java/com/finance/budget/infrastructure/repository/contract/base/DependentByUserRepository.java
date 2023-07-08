package com.finance.budget.infrastructure.repository.contract.base;

import com.finance.budget.domain.DependentByUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface DependentByUserRepository<T extends DependentByUserEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
  Optional<T> findByIdAndUserId(ID id, Long userId);
}

