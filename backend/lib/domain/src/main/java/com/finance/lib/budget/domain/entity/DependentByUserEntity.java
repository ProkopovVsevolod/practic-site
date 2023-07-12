package com.finance.lib.budget.domain.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class DependentByUserEntity {

  @NotNull
  @EmbeddedId
  @GeneratedValue(generator = "composite-sequence-generator")
  @GenericGenerator(
    name = "composite-sequence-generator",
    strategy = "com.finance.lib.budget.domain.generator.CompositeSequenceGeneratorPostgres"
  )
  private CompositeId compositeId = new CompositeId();

  public DependentByUserEntity(Long userId) {
    this.compositeId.setUserId(userId);
  }

  public static class Builder  {
    protected Long userId;

    public Builder userId(Long userId) {
      this.userId = userId;
      return this;
    }
  }

  public static Builder builder() {
    return new Builder();
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
