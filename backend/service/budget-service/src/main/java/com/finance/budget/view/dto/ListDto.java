package com.finance.budget.view.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListDto<E> {
  private Integer elementsCount;
  private Integer offset;
  private Integer diapason;
  private Collection<E> elements;

  public ListDto(Collection<E> elements, Integer offset, Integer diapason) {
    if (offset == null) offset = 0;
    if (diapason == null) diapason = Integer.MAX_VALUE;

    this.elements = elements;
    this.elementsCount = elements.size();
    this.offset = offset;
    this.diapason = diapason;
  }
}
