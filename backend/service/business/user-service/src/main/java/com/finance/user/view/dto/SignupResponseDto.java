package com.finance.user.view.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {
  private Long id;
  private String name;
  private String email;
  private RoleDto role;
}
