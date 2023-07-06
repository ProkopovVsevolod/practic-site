package com.finance.user.view.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;

public enum RoleDto {
  USER, ADMIN;

  private static final Map<String, RoleDto> jsonNamesMap = Map.of(
    "user", USER,
    "admin", ADMIN
  );

  @JsonCreator
  public static RoleDto byName(String name) {
    RoleDto role = jsonNamesMap.get(name.split("ROLE_")[1].toLowerCase());
    if (role != null) {
      return role;
    }
    throw new IllegalArgumentException("Role type: " + name + " is not supported");
  }

  @JsonValue
  public String toValue() {
    for (Map.Entry<String, RoleDto> entry : jsonNamesMap.entrySet()) {
      if (entry.getValue() == this) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("Role type: " + this + " is not supported");
  }
}
