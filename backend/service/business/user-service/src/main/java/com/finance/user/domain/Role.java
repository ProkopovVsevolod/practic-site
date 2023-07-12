package com.finance.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum Role {
  USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

  private String name;

  Role(String name) {
    this.name = name;
  }

  public static Role byName(String name) {
    for (Role role : Role.values()) {
      if (role.name.equals(name)) {
        return role;
      }
    }
    throw new IllegalArgumentException("Role with name: " + name + " not found");
  }
}
