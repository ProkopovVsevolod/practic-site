package com.finance.budget.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DependentByUserEntity<ID extends Serializable> {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private ID id;

  @Column(nullable = false)
  private Long userId;

  public DependentByUserEntity(Long userId) {
    this.userId = userId;
  }
}
