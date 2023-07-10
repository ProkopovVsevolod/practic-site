package com.finance.user.view.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
  private String email;
  private String password;

  @Override
  public String toString() {
    return "LoginDto{" +
      "email='" + email + '\'' +
      '}';
  }
}
