package com.finance.budget.domain.expense;

import com.finance.budget.domain.amount.Amount;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
public class Expense {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_id_sequence")
  @SequenceGenerator(name = "expense_id_sequence", allocationSize = 10)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(columnDefinition = "text")
  private String description;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Column(columnDefinition = "timestamp with time zone")
  private OffsetDateTime dateTime;

  @Enumerated(EnumType.STRING)
  private ExpenseCategory expenseCategory;

  @Embedded
  private Amount amount;
}
