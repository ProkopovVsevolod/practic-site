package com.finance.budget.domain;


import jakarta.persistence.*;
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

  @Column(name = "id")
  private Long id;

  @Column(name = "user_id")
  private Long userId;

}
