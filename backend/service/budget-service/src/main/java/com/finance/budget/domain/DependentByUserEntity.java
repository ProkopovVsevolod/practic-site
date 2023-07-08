package com.finance.budget.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

  public static class Builder<ID extends Serializable>  {
    protected Long userId;

    public Builder<ID> userId(Long userId) {
      this.userId = userId;
      return this;
    }

    public DependentByUserEntity<ID> build() {
      return new DependentByUserEntity<ID>(userId);
    }
  }
  public static <ID extends Serializable> Builder<ID> builder() {
    return new Builder<>();
  }

  public List<Field> getIncludeSuperclassDeclaredFields() {
    List<Field> fieldContainer = new ArrayList<>();
    Class<?> aClass = getClass();
    return getAllFieldsRecursive(aClass, fieldContainer);
  }

  private List<Field> getAllFieldsRecursive(Class<?> curClass, List<Field> fieldContainer) {
    Field[] declaredFields = curClass.getDeclaredFields();
    fieldContainer.addAll(Arrays.asList(declaredFields));
    Class<?> superclass = curClass.getSuperclass();
    if (superclass != null) {
      return getAllFieldsRecursive(superclass, fieldContainer);
    } else {
      return fieldContainer;
    }
  }
}
