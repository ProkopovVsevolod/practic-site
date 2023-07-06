package com.finance.budget.domain.income;

import com.finance.budget.domain.amount.Amount;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
public class Income {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "income_id_sequence")
  @SequenceGenerator(name = "income_id_sequence", allocationSize = 10)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(columnDefinition = "text")
  private String description;

  @Column(nullable = false)
  private String source;

  @Column(columnDefinition = "timestamp with time zone")
  private OffsetDateTime dateTime;

  @Enumerated(EnumType.STRING)
  private IncomeCategory incomeCategory;

  @Embedded
  private Amount amount;
}
