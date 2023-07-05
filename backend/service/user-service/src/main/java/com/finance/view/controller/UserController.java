package com.finance.view.controller;

import com.finance.domain.User;
import com.finance.service.UserService;
import com.finance.view.dto.SignupRequestDto;
import com.finance.view.dto.SignupResponseDto;
import com.finance.view.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseStatus(HttpStatus.OK)
public class UserController {
  private final UserMapper userMapper;
  private final UserService userService;

  @Autowired
  public UserController(UserMapper userMapper,
                        UserService userService) {
    this.userMapper = userMapper;
    this.userService = userService;
  }

  @PostMapping("/api/v1/users/signup")
  public SignupResponseDto signup(@RequestBody SignupRequestDto signupRequestDto) {
    User input = userMapper.convert(signupRequestDto);
    User saved = userService.save(input);
    return userMapper.convert(saved);
  }
}