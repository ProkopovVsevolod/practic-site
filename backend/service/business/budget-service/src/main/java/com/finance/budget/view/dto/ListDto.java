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
    this.elements = elements;
    this.elementsCount = elements.size();
    this.offset = nonNullOffset(offset);
    this.diapason = nonNullDiapason(diapason);
  }

  public ListDto(Collection<E> elements) {
    this.elements = elements;
    this.elementsCount = elements.size();
    this.offset = 0;
    this.diapason = Integer.MAX_VALUE;
  }

  private Integer nonNullOffset(Integer offset) {
    if (offset == null) offset = 0;
    return offset;
  }

  private Integer nonNullDiapason(Integer diapason) {
    if (diapason == null) diapason = Integer.MAX_VALUE;
    return diapason;
  }
}
