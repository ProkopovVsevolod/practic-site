package com.finance.user.view.mapper;

import com.finance.user.domain.Role;
import com.finance.user.domain.User;
import com.finance.user.view.dto.RoleDto;
import com.finance.user.view.dto.SignupRequestDto;
import com.finance.user.view.dto.SignupResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public User convert(SignupRequestDto signupRequestDto) {
    return User.builder()
      .name(signupRequestDto.getName())
      .email(signupRequestDto.getEmail())
      .password(signupRequestDto.getPassword())
      .role(Role.USER)
      .build();
  }

  public Role convert(RoleDto roleDto) {
    return Role.byName(roleDto.name());
  }

  public RoleDto convert(Role role) {
    return RoleDto.byName(role.getName());
  }

  public SignupResponseDto convert(User user) {
    return new SignupResponseDto(
      user.getId(),
      user.getName(),
      user.getUsername(),
      convert(user.getRole())
    );
  }
}
