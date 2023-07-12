package com.finance.lib.budget.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositeId implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @NotNull
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "user_id")
  private Long userId;
}
